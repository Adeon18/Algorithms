"""
Contains Mergesort Merge function but if it was for 3 subarrays.
"""


def merge_3_sorted_lists(A, L, M, R):
    """
    Basically just merge from Mergesort but with 3 lists as input.
    """

    L.append(float("inf"))
    M.append(float("inf"))
    R.append(float("inf"))

    l = m = r = 0

    for k in range(len(A)):
        # Firts 3 checks
        if L[l] <= R[r]:
            if L[l] <= M[m]:
                A[k] = L[l]
                l += 1
            else:
                A[k] = M[m]
                m += 1
        # Second 3 checks
        else:
            if R[r] <= M[m]:
                A[k] = R[r]
                r += 1
            else:
                A[k] = M[m]
                m += 1
    
    return A

if __name__ == "__main__":
    
    print(merge_3_sorted_lists([3, 3, 3, 3, 3, 3, 3, 3, 3], [1, 3, 7], [2, 4, 9], [5, 6, 7]))