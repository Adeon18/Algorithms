"""
This module stores the quick sort algorithm
"""


def partition(arr, start_i, end_i):
    """
    Helper function for quick sort. The goal is to put elements < pivot < elements.
    (Elements don't have to be ordered, recursion does that.)
    """

    pivot_idx = start_i
    pivot = arr[end_i]

    while start_i < end_i:
        # Find the Item from left index
        while start_i < len(arr) and arr[start_i] <= pivot:
            start_i += 1
        # Find the item from right index
        while arr[end_i] > pivot:
            end_i -= 1
        # If their indexes don't cross, spaw them
        if start_i < end_i:
            arr[start_i], arr[end_i] = arr[end_i], arr[start_i]
    arr[end_i], arr[pivot_idx] = arr[pivot_idx], arr[end_i]
     
    return end_i


def quick_sort(arr, start_i, end_i):
    """
    Recursive quick sort algo that uses partition function.
    """
    if start_i < end_i:

        part_i = partition(arr, start_i, end_i)


        quick_sort(arr, start_i, part_i - 1)
        quick_sort(arr, part_i + 1, end_i)


if __name__ == "__main__":
    test = [6, 3, 7, 9, 2, 1, 0]
    quick_sort(test, 0, len(test) - 1)

    print(test)

