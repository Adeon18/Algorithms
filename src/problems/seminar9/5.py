
A_arr = [2, 1, 1, 200]
B_arr = [1, 1, 20, 100]
cache_A = [None] * 4 
cache_B = [None] * 4 
a = [0]

def SuperPCOpt(i, O):
    
    if i in [-2, -1]:
        return 0
    elif cache_A[i] and O == A_arr:
        return cache_A[i]
    elif cache_B[i] and O == B_arr:
        return cache_B[i]
    else:
        a[0] += 1
        if O == A_arr:
            cache_A[i] = O[i] + max(SuperPCOpt(i-1, A_arr), SuperPCOpt(i-2, B_arr))
            return cache_A[i]
        elif O == B_arr:
            cache_B[i] = O[i] + max(SuperPCOpt(i-1, B_arr), SuperPCOpt(i-2, A_arr))
            return cache_B[i]
        else:
         
            print("this shouldn't happen")

def solution(arr_len):
    return max((SuperPCOpt(arr_len-1, A_arr)), SuperPCOpt(arr_len-1, B_arr))

if __name__ == "__main__":
    print(solution(4))
    print(cache_A)
    print(cache_B)

    # print(SuperPCOpt(3, B_arr), "AAAAAAAAA")