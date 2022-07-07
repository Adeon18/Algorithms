#include <iostream>
#include <vector>
#include <unordered_map>

class Solution {
public:
    int romanToInt(std::string s) {
        std::unordered_map<char, int> symbols{{'I', 1}, {'V', 5}, {'X', 10}, {'L', 50}, {'C', 100}, {'D', 500}, {'M', 1000}};
        
        int res = 0;
        for (size_t i = 0; i < s.size(); ++i) {
            if (i < s.size() - 1) {
                if (symbols[s[i]] < symbols[s[i+1]]) {
                    res += symbols[s[i+1]] - symbols[s[i]];
                    ++i;
                    continue;
                }
            }
            res += symbols[s[i]];
        }
        return res;
    }
};

int main() {
    Solution s;

    std::cout << s.romanToInt("MCM") << std::endl;
}