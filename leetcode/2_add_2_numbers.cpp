/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int carry = 0;
        ListNode *res = new ListNode(0);
        ListNode *mov_res = res;
        
        while (true) {
            int sum = 0;
            if (l2 != nullptr) {
                sum += l2->val;
                l2 = l2->next;
            }

            if (l1 != nullptr) {
                sum += l1->val;
                l1 = l1 -> next;
            }
            sum += carry;
            carry = sum / 10;
            mov_res->val = sum % 10;
            if (l1 != nullptr || l2 != nullptr || carry) {
                ListNode *nxt = new ListNode(0);
                mov_res->next = nxt;
                mov_res = mov_res->next;
            } else {
                break;
            }
        }
        return res;
    }
};