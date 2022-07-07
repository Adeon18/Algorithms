#include <iostream>
#include <vector>

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".
*/


class Solution {
public:
    std::string longestCommonPrefix(std::vector<std::string>& strs) {
        
        std::string sbstr{strs[0]};
        size_t len = sbstr.size();
        
        for (int i = 1; i < strs.size(); ++i) {
            while (sbstr != strs[i].substr(0, len)) {
                --len;
                sbstr = sbstr.substr(0, len);
                if (len == 0) break;
            }
        }
        return sbstr;
    }
};


int main() {
    return 0;
}