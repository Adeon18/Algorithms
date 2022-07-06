"""
LeetCode Problem 132: Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.
"""

import pprint


class Solution:
    def minCut(self, s: str) -> int:
        
        str_len = len(s)
        cuts = [float('inf')] * str_len
        # Build the Matrix for true-false statements
        palindrome_matrix = [[False] * str_len for _ in range(str_len)]
        # For palingromes of length...
        for pal_len in range(1, str_len+1):
            # For every palindrome of that len...
            for start in range(str_len - pal_len + 1):
                end = start + pal_len - 1
                # start filling out our matrix
                if s[start] == s[end] and (start+1 > end-1 or palindrome_matrix[start+1][end-1]):
                    palindrome_matrix[start][end] = True
                else:
                    palindrome_matrix[start][end] = False
            
        # We move bottom-up and check if a given string in a palindrome.
        # If it is, we do 0 cuts and move on.
        # If it is NOT, we find an index in the same row, where we can divide so we get a palindrome
        # and then our cuts is 1(for index we already found) + min cuts of the col_j+1.. substring.
        for row_i in range(str_len-1, -1, -1):
            if palindrome_matrix[row_i][str_len-1]:
                cuts[row_i] = 0
            else:
                for col_j in range(row_i, str_len):
                    if palindrome_matrix[row_i][col_j]:
                        cuts[row_i] = min(cuts[row_i], 1+cuts[col_j+1])
        # pprint.pprint(cuts)
        # pprint.pprint(palindrome_matrix)
        return cuts[0]



if __name__ == "__main__":
    strr = "abaqtqa"

    s = Solution()

    print(s.minCut(strr))