"""
LeetCode Problem 76: Minimum Window Substring: hard.

Given two strings s and t of lengths m and n respectively,
return the minimum window substring of s such that every character in t
(including duplicates) is included in the window.
If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

Solution:

We use a Sliding window approach with pointers l and r and store the t str data in a dict.
s = ["D", "B", "D", "C", "A"]
t = "DBC"
t_dict = {D:1; B:1; C:1}

Step 1: We increment out pointer r until we have a substring with all the needed letters from t.
We monitor it with t_dict(Values there can go into negative).

  l
["D", "B", "D", "C", "A"]
                 r

Step 2: We move our pointer l if we encounter a letter that is not in t_dict,
OR if the t_dict[letter] < 0, in other words there is a trailing duplicate.

       l
["D", "B", "D", "C", "A"]
                 r

Step 3: Then we save the smallest substr len and data and repeat step 1 and 2 until we
come to the array end. Then we return the smallest substr.

The time complexity is O(len(s) + len(t)) because we loop s times and build a has table of length t.
"""


def minWindow(s: str, t: str) -> str:

    # The T string letter data will be here
    t_letter_data = {}

    # Initialise both left and right pointers at the string start
    right_p = left_p = 0
    # Characters left till the current substring contains >= the amount of needed chars.
    chars_left = len(t)
    # The min len
    subst_min_len = float("inf")
    substr_min = ""
    # Make a dict of needed chars
    for char in t:
        if char in t_letter_data:
            t_letter_data[char] += 1
        else:
            t_letter_data[char] = 1
    
    while right_p < len(s):
        cur_right_char = s[right_p]

        # If the char is in the needed chars dict, we decrease from the chars_left and
        # t_letter_data[cur_char], because we found 1 of the needed letters.
        if cur_right_char in t_letter_data:
            t_letter_data[cur_right_char] -= 1
            # We decrement chars_left only if we still have letters in t_letter_data values(not < 0)
            if t_letter_data[cur_right_char] >= 0:
                chars_left -= 1
        # Since we found all the chars we move the left pointer now
        if chars_left == 0:
            # The left limit of substr
            cur_left_char = s[left_p]

            # Wemove the left pointer only when removing unwanted letters or trailing duplicates
            while cur_left_char not in t_letter_data or t_letter_data[cur_left_char] < 0:
                # If the left char is in keys, increment the key value
                if cur_left_char in t_letter_data:
                    t_letter_data[cur_left_char] += 1
                # Move further left
                left_p += 1
                cur_left_char = s[left_p]
            # Find the new str_len
            cur_substr_len = right_p - left_p - 1
            # Replace the old one with the new one if smaller
            if cur_substr_len <= subst_min_len:
                subst_min_len = cur_substr_len
                substr_min = s[left_p:right_p+1]
        
        right_p += 1

    return substr_min


if __name__ == "__main__":
    test_s = ["D", "B", "D", "C", "A"]
    test_t = "DBC"

    print(minWindow(test_s, test_t))
    
