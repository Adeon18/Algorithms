#include <iostream>
#include <unordered_map>
#include <stack>

/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.

0 ms runtime!
*/

class Solution {
public:
    bool isValid(std::string s) {
        std::unordered_map<char, char> um{{'(', ')'}, {'{', '}'}, {'[', ']'}};
        std::stack<char> stk;
        
        for (int i = 0; i < s.size(); ++i) {
            if (stk.empty()) {
                if (um.count(s[i]) != 0) {
                    stk.push(s[i]);
                } else {
                    return false;
                }
            } else {
                if (s[i] == um[stk.top()]) {
                    stk.pop();
                } else if (um.count(s[i]) != 0) {
                    stk.push(s[i]);
                } else {
                    return false;
                }
            }
        }
        if (stk.empty()) {
            return true;
        }
        return false;
    }
};

int main() {
    Solution s;

    std::cout << s.isValid("[]") << std::endl;
    return 0;
}