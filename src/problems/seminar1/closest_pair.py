"""
Find the closest pair in numbers in an array.
Time complexity must be O(nlogn)
"""


def merge_sort(arr):
    """
    A slightly rewritten version of the merge sort from geeksforgeeks.com
    """
    if len(arr) > 1:

        mid = len(arr) // 2

        l_arr = arr[:mid]
        r_arr = arr[mid:]

        merge_sort(l_arr)
        merge_sort(r_arr)

        i = j = k = 0
        # print(l_arr, r_arr, arr)
        # Start merging the data from the small lists to the big lists recursively
        while i < len(l_arr) and j < len(r_arr):
            if l_arr[i] < r_arr[j]:
                arr[k] = l_arr[i]
                i += 1
            else:
                arr[k] = r_arr[j]
                j += 1
            k += 1
  
        # Checking for trailing elements in left and right list
        while i < len(l_arr):
            arr[k] = l_arr[i]
            i += 1
            k += 1
  
        while j < len(r_arr):
            arr[k] = r_arr[j]
            j += 1
            k += 1


def closest_pair(arr):
    """
    Find the closest pair of numbers in an unsorted list.
    Time complexity must be O(nlogn)
    """
    merge_sort(arr)

    min_diff = float('inf')
    closest_nums = []
    for i in range(1, len(arr) - 1):
        if abs(arr[i] - arr[i-1]) < min_diff:
            min_diff = abs(arr[i] - arr[i-1])
            closest_nums = [arr[i-1], arr[i]]
    
    return closest_nums


if __name__ == '__main__':
    test = [5, 4, 8, 2, 9]
    print(closest_pair(test))
    print(test)

