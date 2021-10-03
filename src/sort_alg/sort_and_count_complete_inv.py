"""
This is a merge sort that counts all the absolute inversions
"""


def sort_and_count_complete_invertions(arr):
    """
    Sorts a given array and counts invertions.

    Time is O(nlogn)
    """

    n = len(arr)

    if n == 1:
        return (arr, 0)
    else:
        # Stores left subarray and left invertions
        left = sort_and_count_complete_invertions(arr[:n//2])
        # Stores right subarray and right invertions
        right = sort_and_count_complete_invertions(arr[n//2:])
        print("Left, Right",left, right, flush=True)
        # Stores split inversions
        split_i = merge_and_sort_split_complete_invertions(arr, left[0], right[0])

        return (arr, left[1] + right[1] + split_i[1])


def merge_and_sort_split_complete_invertions(arr, larr, rarr):
    """
    Merges split invertions for sort_and_count_invertions.
    Counts the invertions that go through the middle
    """

    n1 = len(larr)
    n2 = len(rarr)


    larr.append(float("inf"))
    rarr.append(float("inf"))


    i = 0
    j = 0
    k = 0
    inv = 0     # Invertions counter
    # Merge 2 lists while counting their invertions
    for k in range(len(arr)):
        print(larr, rarr, flush=True)
        ##########################
        # Here is the code that accounts for inversions
        if larr[i] > 2*rarr[j]:
            inv += (n1 - i)
        ###########################
        if larr[i] <= rarr[j]:
            arr[k] = larr[i]
            i += 1
        else:
            arr[k] = rarr[j]
            j += 1


    return (arr, inv)


if __name__ == "__main__":
    test = [1, 20, 7, 4, 6, 4, 5 ]
    test2 = [1, 2, 3, 4, 5]
    test3 = [1, 3, 2, 0, -1]

    print(sort_and_count_complete_invertions(test))
            

