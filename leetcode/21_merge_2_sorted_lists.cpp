#include <iostream>

/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.
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
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
        ListNode *res = new ListNode();
        
        if (list1 == nullptr && list2 == nullptr) return nullptr;
        
        ListNode *mres = res;
        while (list1 != nullptr && list2 != nullptr) {
            if (list1->val < list2->val) {
                mres->val = list1->val;
                list1 = list1->next;
            } else {
                mres->val = list2->val;
                list2 = list2->next;
            }
            mres->next = new ListNode();
            mres = mres->next;
        }

        ListNode *dangling = (list1 == nullptr) ? list2 : list1;
        while (dangling != nullptr) {
            mres->val = dangling->val;
            dangling = dangling->next;
            if (dangling == nullptr) {
                break;
            }
            mres->next = new ListNode();
            mres = mres->next;
        }
        return res;
    }
};

int main() {
    Solution s;
    s.mergeTwoLists(new ListNode(1, new ListNode(2, new ListNode(5))), new ListNode(2, new ListNode(4, new ListNode(6))));

    return 0;
}