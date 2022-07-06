"""
Find a number in a sorted array of unique numbers.
Use O(logn)
"""


def find_number(arr, num, low, high):
    """
    Check if the number is present in the list using only O(logn).
    """
    if (high - low == 0) or (high - low)/abs(high - low) == 1:
        mid = low + (high - low) // 2
        # Binary search
        if num - arr[mid] == 0:
            return True
        elif (arr[mid] - num)/abs(arr[mid] - num) == -1:
            return find_number(arr, num, mid+1, high)
        elif (arr[mid] - num)/abs(arr[mid] - num) == 1:
            return find_number(arr, num, low, mid-1)
    return False


if __name__ == "__main__":
    test = [1, 2, 4, 6, 7, 8]
    print(find_number(test, 7, 0, len(test)-1))
