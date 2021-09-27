def findMedian(nums1, nums2):
    INT_MIN = -99999999999999
    INT_MAX = 99999999999999
    n = len(nums1)
    l, r = 0, n - 1
    s1 = 'nums1\n' + ''.join(map(lambda x: "{:3}".format(x), nums1))
    s2 = 'nums2\n' + ''.join(map(lambda x: "{:3}".format(x), nums2))

    print('=========start=========')
    while True:
        print(f'\nl  = {l},  r  = {r}')
        i1 = (l + r) // 2
        i2 = n - i1 - 2
        print(f'i1 = {i1}, i2 = {i2}')
        print(s1)
        if l != i1:
            print(' ' * (3*l+2) + 'l' + ' ' * (3*(i1-l-1)+1) + 'i1' + ' ' * (3*(r-i1-1)+1) + 'r')
        elif i1 != r:
            print(' ' * (3*l+2) + 'l' + ' ' * (3*(r-l-1)+1) + 'r')
            print(' ' * (3*i1+1) + 'i1')
        else:
            print(' ' * (3*l+2) + 'l')
            print(' ' * (3*i1+1) + 'i1')
            print(' ' * (3*r+2) + 'r')
        print(s2)
        print(' ' * (3*i2+1) + 'i2')
        val1L = nums1[i1] if i1 >= 0 else INT_MIN
        val1R = nums1[i1 + 1] if i1 < n - 1 else INT_MAX
        val2L = nums2[i2] if i1 >= 0 else INT_MIN
        val2R = nums2[i2 + 1] if i1 < n - 1 else INT_MAX

        if val1L < val2R and val2L < val1R:
            print('==========end==========\n')
            return max(val1L, val2L)
        elif val1L > val2R:
            print(f'nums1[i1] > nums2[i2+1]')
            r = i1 - 1
            print(f'r = i1-1 = {i1}-1 = {r}')
        elif val2L > val1R:
            print(f'nums2[i2] > nums1[i1+1]')
            l = i1 + 1
            print(f'l = i1+1 = {i1}+1 = {l}')


if __name__ == '__main__':
    nums1 = [1, 3, 5,  7,  8,  9, 12, 14]
    nums2 = [2, 4, 6, 10, 11, 13, 15, 16]
    print(findMedian(nums1, nums2))