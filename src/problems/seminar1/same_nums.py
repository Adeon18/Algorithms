"""
You have 2 sorted arrays with len n. Print out the same numbers that they have.
The time should be O(n)
"""

def find_same(arr1: list, arr2: list):
    """
    Find same numbers in 2 sorted lists.
    Time must be O(n)
    """
    i, j = 0, 0
    print("", end="|")
    while i < len(arr1) and j < len(arr2):
        if arr1[i] == arr2[j]:
            print(arr1[i], end="|")
            i += 1
            j += 1
        elif arr1[i] < arr2[i]:
            i += 1
        elif arr1[i] > arr2[i]:
            j += 1
    print()


if __name__ == "__main__":

    test1 = [1, 2, 3, 4, 7]
    test2 = [2, 3, 7, 7, 8]
    find_same(test1, test2)
