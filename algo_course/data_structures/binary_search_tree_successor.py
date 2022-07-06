"""
A binary tree implementation with the successor instead of a parent.
For now only everything needed for proper implementation.
BIG TODO
"""


class BSTNode:
    def __init__(self, val=None):
        self.left = None
        self.right = None
        self.val = val
        self.successor = None

    def get_left(self):
        return self.left
    
    def get_right(self):
        return self.right
    
    def max_el(self, node):
        """
        Find the max node in the tree and return it.
        """
        while node.right != None:
            node = node.right
        return node
    
    def min_el(self, node):
        """
        Find the min node in the tree and return it.
        """
        while node.left != None:
            node = node.left
        return node
    
    def search(self, node, key):
        """
        Binary search the key in the tree.
        Search remains the same as we don't need the parent.
        """
        if node == None or node.val == key:
            return node
        
        if key < node.val:
            return self.search(node.left, key)
        else:
            return self.search(node.right, key)
    
    def get_succsessor(self, root_node, node):
        if node.right:
            return self.max_el(node.right)
        
        # if root_node == None or root_node.val == key:
        #     return node
        
        successor = None
        if root_node.val > node.val:
            successor = root_node;
            root_node = root_node.left;
        elif root_node.val < node.val: 
            root_node = root_node.right;
        else:
            return successor;
        
    
    def insert(self, root_node, val):
        """
        Insert a new node into the binary search tree.
        """
        new_node = BSTNode(val)
        successor_node = None
        search_node = root_node
        # iterartions = 0
        # Search for place to insert value
        successor_node = self.get_succsessor(search_node, new_node)
        # If there is no root node, well ther will be now   
        new_node.successor = successor_node
        pass
        
    
    def inorder_walk(self, node):
        """
        Walk through the tree by accessing left child - self - left child.
        """

        if node != None and node.val != None:
            self.inorder_walk(node.get_left())
            print(node.val, end=',')
            self.inorder_walk(node.get_right())
        return None
    
    def to_sorted_dlinked_list(self, node):
        """
        Turn the array into sorted doubly linked list.
        """
        if node != None and node.val != None:
            self.inorder_walk(node.left)
            
            pass

        return None


if __name__ == "__main__":
    tree = BSTNode(5)
    tree.insert(7)
    print("New El")
    tree.insert(3)
    print("New El")
    tree.insert(2)
    print("New El")
    tree.insert(4)
    print("New El")
    tree.insert(6)
    # print(tree.inorder_walk(tree))
    # print(tree.min_el(tree).val)
    # print(tree.max_el(tree).val)
    # converter = BTtoDLLConverter()
    # dll_from_tree = converter.convert(tree)
    # print_dll(dll_from_tree)