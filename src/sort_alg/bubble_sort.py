"""
Bubble sort is good to detect small sorting errors or checking if the list is sorted.
But is is terrible for sorting badly sorted lists.
"""


def bubble_sort(arr):
    """
    Bubble sort implementation.
    The principle of the sort is to compare 2 neighbor numbers
    and replace their positions if they are not sorted.
    Then we do this enough times so that the entire list is sorted.
    Also the second for loop does not go all the way through because we know that
    the last/2last/3last elements are already the largest after the 1/2/3rd sort.
    """
    arr_len = len(arr)
    for i in range(arr_len - 1):
        for j in range(arr_len - 1 - i):
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]
            print(arr)



if __name__ == "__main__":
    test_arr = [5, 4, 3, 2]
    print(bubble_sort(test_arr))
