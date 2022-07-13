#include <iostream>
#include <cmath>


/*
Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.

The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).

The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
*/


class Solution {
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area = 0;
        area += computeRectangeArea(ax1, ay1, ax2, ay2);
        area += computeRectangeArea(bx1, by1, bx2, by2);
        
        if (isCollidingAABB(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2)) {
            area -= computeRectangeArea(std::max(ax1, bx1), std::max(ay1, by1), std::min(ax2, bx2), std::min(ay2, by2));
        }
        
        return area;
    }
    
    int computeRectangeArea(int x1, int y1, int x2, int y2) {
        return std::abs(x2 - x1) * std::abs(y2 - y1);
    }
    // Simple AABB colliding checker
    bool isCollidingAABB(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return (ax2 > bx1 && ay2 > by1 && ax1 < bx2 && ay1 < by2);
    }
};


int main() {
    return 0;
}