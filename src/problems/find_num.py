"""
Find a number in a sorted array of unique numbers.
Use O(logn)
"""
from numpy import sign


def find_number(arr, num, low, high):
    """
    Check if the number is present in te list using only O(logn).
    """
    if high >= low:
        mid = low + (high - low) // 2
        # Binary search
        if sign(arr[mid] - num) == 1:
            return find_number(arr, num, low, mid-1)
        elif sign(arr[mid] - num) == -1:
            return find_number(arr, num, mid+1, high)
        elif num - arr[mid] == 0:
            return True
    return False


if __name__ == "__main__":
    test = [1, 2, 4, 6, 7, 8]
    print(find_number(test, 2, 0, len(test)-1))
