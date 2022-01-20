

is_placed = [False, True, False, True, False]
prices = [3, 2, 5, 5, 1]
global_min = [float('inf')]

min_lst = [float('inf')] * len(is_placed)

def OptSolve(n):
    minn = 0
    for j in range(n-1):
        if is_placed[j]:
            print( prices[n-1])
            print(sum(range(n-j-1)))
            minn = prices[n-1] + OptSolve(j) + sum(range(n-j-1))
            min_lst[j] = minn
            if minn < global_min[0]:
                global_min[0] = minn
        # print("Called")
    return minn


print(OptSolve(5))
print(min_lst)
print("MIN =", global_min[0])