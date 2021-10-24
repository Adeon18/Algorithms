"""
A binary search tree implementation.
TODO: Successor and Delete functions.
"""


class BSTNode:
    def __init__(self, val=None):
        self.left = None
        self.right = None
        self.val = val
        self.parent = None
    
    def get_root_node(self):
        root = self
        while self.parent != None:
            root = self.parent
        return root
    
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
    
    def insert(self, val):
        """
        Insert a new node into the binary search tree.
        """
        new_node = BSTNode(val)
        parent_node = None
        search_node = self.get_root_node()
        # iterartions = 0
        # Search for place to insert value
        while search_node != None:
            # iterartions += 1
            # print(iterartions, flush=True)
            parent_node = search_node
            if new_node.val < search_node.val:
                search_node = search_node.get_left()
            else:
                search_node = search_node.get_right()
        # If there is no root node, well ther will be now   
        new_node.parent = parent_node
        if parent_node == None:
            root = self.get_root_node()
            new_root = new_node
            new_root.left = root.left
            new_root.right = root.right
        else:
            # Else append the new node to the end
            if new_node.val < parent_node.val:
                parent_node.left = new_node
            else:
                parent_node.right = new_node
    
    def search(self, node, key):
        """
        Binary search the key in the tree.
        """
        if node == None or node.val == key:
            return node
        
        if key < node.val:
            return self.search(node.left, key)
        else:
            return self.search(node.right, key)
    
    def inorder_walk(self, node):
        """
        Walk through the tree by accessing left child - self - left child.
        """

        if node != None and node.val != None:
            self.inorder_walk(node.get_left())
            print(node.val, end=',')
            self.inorder_walk(node.get_right())
        return None
    
    def remove_where_no_1(self, root_node):
        """
        Remove all the subtrees of a binary tree where there is no digit 1.
        """
        if self.search(root_node, 1) == None:
            return None
        
        if self.search(root_node.left, 1) != None:
            self.remove_where_no_1(root_node.left)
        else:
            print("Remove left")
            root_node.left = None
        
        if self.search(root_node.right, 1) != None:
            self.remove_where_no_1(root_node.right)
        else:
            print("Remove right")
            root_node.right = None


class BTtoDLLConverter:
    """
    A converter to convert a binary Serch tree to a Doubly Linked List.
    """
    def __init__(self):
        self.head = None
        self.tail = None
 
    def convert(self, root):
       
        # Base case
        if root is None:
            return
           
        # Recursively convert left subtree
        self.convert(root.left)
         
        # Now convert this node
        node = root
        # This basically is an inorder traversal
        if self.head is None:
            self.head = node
        else:
            self.tail.right = node
            node.left = self.tail
        # Set the tail to the node
        self.tail = node
         
        # Finally convert right subtree
        self.convert(root.right)
        return self.head


def print_dll(head):
    """
    Print the doubly linked list.
    """
    while head is not None:
        print(head.val, end=" ")
        head = head.right


if __name__ == "__main__":
    tree = BSTNode(1)
    
    tree.left = BSTNode(0)
    tree.left.parent = tree

    tree.left.left = BSTNode(0)         # Is deleted
    tree.left.left.parent = tree.left
    tree.left.right = BSTNode(1)        # Stays
    tree.left.right.parent = tree.right


    tree.right = BSTNode(0)
    tree.right.parent = tree

    tree.right.left = BSTNode(0)        # Is deleted
    tree.right.left.parent = tree.right
    tree.right.right = BSTNode(1)       # Stays
    tree.right.right.parent = tree.right
    
    tree.remove_where_no_1(tree)

    print(tree)