"""
Here lies insertion_sort.
"""

def insertion_sort(arr):
    """
    Insertion sort algoritm
    """
    # Move through 1 to len(arr)
    for i in range(1, len(arr)):
        key = arr[i]
        # Move els of [0, i-1] that are greater than key 1 pos ahead
        # Move the key to the pos where it is bigger than the prev num(j+1)
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j+1] = arr[j]
            j -= 1
        arr[j+1] = key

if __name__ == "__main__":
    # Driver code to test above
    input_arr = [12, 11, 13, 5, 6, 7, 8, 1, 2]
    print("Input Array", input_arr)
    insertion_sort(input_arr)
    print("Sorted Array", input_arr)
