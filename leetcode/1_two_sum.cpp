#include <iostream>
#include <vector>
#include <unordered_map>

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.
*/

class Solution {
public:
    std::vector<int> twoSum(const std::vector<int>& nums, int target) {
        
        std::unordered_map<int, int> hashmap;
        int i = 0;
        
        for (auto const &el: nums) {
            int cur = hashmap[target - el];
            ++i;
            if (cur != 0 && cur - 1 != i) {
                return std::vector{i-1, cur-1};
            } else {
                hashmap[el] = i;
            }
        }
        return std::vector{0, 100};
    }
};

int main() {
	Solution s;
	auto res = s.twoSum(std::vector{1, 2, 4, 6}, 8);
	std::cout << "[" << res[0] << ", " << res[1] << "]" << std::endl;
}
