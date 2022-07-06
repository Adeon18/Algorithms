"""
This function find all quoters a, b, c, d, so that a + b = c + d in a array.
"""

def count_quads(arr):
    """
    Find and print all the quads so that a + b = c + d;
    Print the pairs;
    Return the counter;
    """
    htable = {}
    num_of_quads = 0

    for i in range(len(arr)):
        for j in range(i):
            cur_sum = arr[i] + arr[j]

            if cur_sum in htable:
                num_of_quads += 1
                print(f"sum{htable[cur_sum]} == sum({arr[i]}, {arr[j]}) == {arr[i] + arr[j]}")
            else:
                htable[cur_sum] = (arr[i], arr[j])
    
    return num_of_quads


if __name__ == "__main__":
    test = [1, 5, 2, 0, 8, 3]
    print(count_quads(test))