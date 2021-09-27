

def matrix_search(matx, num):

    i = 0
    r = len(matx)

    while(r - i < num):
        mid = (i+r) // 2

        if matx[mid] < num:
            i = mid
        else:
            r = mid
        
    if matx[i] >= num:
        return i
    else:
        return r


if __name__ == "__main__":
    test = [[1, 2, 3], [3, 4, 5], [6, 7, 8]]
    print(matrix_search(test, 5))