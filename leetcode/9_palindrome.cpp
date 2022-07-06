#include <iostream>

/*
Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward.

    For example, 121 is a palindrome while 123 is not.
*/

class Solution {
public:
    bool isPalindrome(int x) {
        auto s = std::to_string(x);
        
        bool res = true;
        int sz_main = s.size();
        int sz = s.size();
        if (s.size() % 2 != 0) {
            --sz;
        }

        for (int i = 0; i < sz / 2; ++i) {
            if (s[i] != s[sz_main - 1 - i]) {
                res = false;
            }
        }
        return res;
    }
};