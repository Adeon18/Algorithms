#include <iostream>
#include <unordered_map>

/*
Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
Return the linked list sorted as well.
*/

// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode* head_cp = head;
        ListNode *prev = head;
        std::unordered_map<int, bool> hmap;
        
        while (head_cp != nullptr) {
            if (hmap.count(head_cp->val)) {
                prev->next = head_cp->next;
            } else {
                hmap[head_cp->val] = true;
                prev = head_cp;
            }
            head_cp = head_cp->next;
        }
        return head;
    }
};

int main() {
    Solution s;

    return 0;
}