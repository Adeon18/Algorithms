"""
Here lies the mergesort algorithm
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



if __name__ == "__main__":
    test = [6, 3, 8, 2, 1, 4, 9]
    merge_sort(test)
    print(test)