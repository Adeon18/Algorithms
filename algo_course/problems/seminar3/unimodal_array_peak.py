"""
This is an algorithm to find the Peak of an unimodal(1, 2,3, 4, 5, 6, 3, 2, 1) array peak.
"""


def find_peak(arr, start, end):
    """
    Find the peak element idx in a unimodal array
    """
    arr_len = end - start + 1

    mid = arr_len//2
    # Too much to the right
    if arr[mid] < arr[mid - 1]:
        find_peak(arr, start, mid-1)
    # Too much to the left
    elif arr[mid+1] > arr[mid]:
        find_peak(arr, mid+1, end)
    else:
        return mid


if __name__ == "__main__":
    test = [1, 2, 3, 5, 6, 7, 4, 3, 2, 1]
    print(find_peak(test, 0, len(test) - 1))




