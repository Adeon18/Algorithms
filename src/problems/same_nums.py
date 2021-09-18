"""
You have 2 sorted arrays with len n. Print out the same numbers that they have.
The time should be linear.
UNFINISHED
"""

def find_same(arr1, arr2):

    # The fist same number index
    index_difference = 0
    for i in range(len(arr1)):
        if arr1[i] == arr2[0]:
            index_difference = i
        if arr1[i] == arr2[i - index_difference]:
            print(arr1[i], end=',')


if __name__ == "__main__":

    test1 = [1, 2, 3, 4, 5]
    test2 = [3, 5, 6, 7, 8]
    find_same(test1, test2)
