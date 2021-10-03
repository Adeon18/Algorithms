"""
This module stores the quick sort algorithm
"""
import math


def partition(arr, start, end):
    """
    Helper function for quick sort. The goal is to put elements < pivot < elements.
    (Elements don't have to be ordered, recursion does that.)
    """

    pivot = arr[end]

    wall = start - 1

    for curr_el in range(start, end):
        if arr[curr_el] <= pivot:
            wall += 1
            arr[wall], arr[curr_el] = arr[curr_el], arr[wall]

    arr[wall+1], arr[end] = arr[end], arr[wall+1]

    return wall+1


def modified_partition(arr, start, end):
    """
    This partition is modified so that if all the arr els are the same,
    q = floor((start+end)/2)
    """
    pivot = arr[end]

    wall = start - 1

    wall2 = start - 1

    for curr_el in range(start, end):
        # If the element is == to pivot, we throw it to where the wall is, 
        # But if the element is smaller then pivot, we throw it where the wall is, even if there
        # is an element that is the same to pivot. el < pivot == copy of pivot
        if arr[curr_el] < pivot:
            wall += 1
            wall2 += 1
            arr[wall], arr[curr_el] = arr[curr_el], arr[wall]
        elif arr[curr_el] == pivot:
            wall2 += 1
            arr[wall2], arr[curr_el] = arr[curr_el], arr[wall2]

    arr[wall2+1], arr[end] = arr[end], arr[wall2+1]

    return math.floor((wall + wall2) / 2) + 1



def quick_sort(arr, start, end):
    """
    Recursive quick sort algo that uses partition function.
    """
    if start < end:

        part_i = partition(arr, start, end)
        print(part_i)
        # print(part_i)

        quick_sort(arr, start, part_i - 1)
        quick_sort(arr, part_i + 1, end)


def non_recursive_quick_sort(arr, start, end):

    # We create a stack of nums
    stack = [0 for _ in range(len(arr))]
  
    # We crea
    stack_i = -1
  
    # Push initial strating end ending indexes to the stack
    stack_i += 1
    stack[stack_i] = start
    stack_i += 1
    stack[stack_i] = end
  
    # Keep popping from stack while is not empty
    while stack_i >= 0:
        print("New Iteration")
        print(stack)
        # Pop 2 of the top indexes - new start and end
        end = stack[stack_i]
        stack_i -= 1
        start = stack[stack_i]
        stack_i -= 1
  
        # Set pivot element at its correct position in a
        # sorted array
        # Also start and end are the last 2 elements of the array
        pivot_pos = partition(arr, start, end)
        print("Start =", start, "End =", end)
        print("Pivot Idx =", pivot_pos)
        print(stack)
        # If there is something to the left of the pivot, we push it on to our stack
        # It will be accessed later and we eill evetually hit the point where
        # Left part of Recursion
        if pivot_pos - 1 > start:
            stack_i += 1
            # New start
            stack[stack_i] = start
            stack_i += 1
            # New End
            stack[stack_i] = pivot_pos - 1
        print(stack)
  
        # If there are elements on right side of pivot,
        # then push right side to stack
        # Right Part of Recursion
        if pivot_pos+1 < end:
            stack_i += 1
            # New start
            stack[stack_i] = pivot_pos + 1
            stack_i += 1
            # New end
            stack[stack_i] = end
        print(stack)


if __name__ == "__main__":
    test = [3, 3, 3, 3, 3, 3]
    quick_sort(test, 0, len(test) - 1)

    print(test)

