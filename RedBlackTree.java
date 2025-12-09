public class RedBlackTree{

    /* This is a basic implementation of the RedBlackNode interface that could
       be a starting point for your own implementation. You are welcome to change 
       (or completely remove) this definition, as long as the node type you create
       implements the RedBlackNode interface.

       Remember that if your code does not properly implement the necessary interfaces,
       we will be unable to mark it and it will receive a mark of zero.
    */
    private class Node implements RedBlackNode{
        public Node(int key, Node left, Node right, boolean red){
            this.key = key;
            this.left = left;
            this.right = right;
            this.red = red;
        }
        public int key(){
            return key;
        }
        public boolean isRed(){
            return red;
        }
        public RedBlackNode leftChild(){
            return left;
        }
        public RedBlackNode rightChild(){
            return right;
        }
        

        public int key;
        public Node left, right;
        public boolean red;
    }
    
    public RedBlackTree(){
        // Add code as needed
        // (You can add other constructors as well, but this default constructor will be used
        //  for testing)
        root = null;
    }

    //Checking if node is red
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.red;
    }

    //performing a left rotation at node h, this is used when the right child is red and left child is black
    //Runtime : O(1) 
    private Node rotateLeft(Node h) {
        Node x = h.right; //x is the new root of the subtree
        h.right = x.left;  //transfer x's left subtree to h's right subtree
        x.left = h; //h is the left child of x
        x.red = h.red; //x takes h color
        h.red = true; //h becomes red
        return x;
    }

    //rotating right, used when there are two consecutive red links on the left
    //Runtime : O(1)
    private Node rotateRight(Node h) {
        Node x = h.left; 
        h.left = x.right;
        x.right = h;
        x.red = h.red;
        h.red = true;
        return x;
    }

    //flip colors of node and its children, used to split a 4-node, black node with two red children
   //after flipping h becomes red, its red children becomes black
    private void flipColors(Node h) {
        h.red = true;
        h.left.red = false;
        h.right.red = false;
    }

    /**
     * Attempt to insert the provided key into the tree.
     * If the key is not already in the tree, add it to the tree and return true.
     * If the key is already in the tree, return false without modifying the tree.
     * @return true if the key was added, false if the key was not added due to being present already
     */
    public boolean insert(int key){
        if (find(key)) {
            return false;
        }
        
        root = insertHelper(root, key);
        root.red = false;
        return true;
    }

    //recursive helper method for insertion, implements the left-leaning red black tree insertion alogirthm
   //implementing standard BST insertion followed by rotation
   //Runtime : O(log n)
    private Node insertHelper(Node h, int key) {
        //Base case: if insersion point is found, create a new red node
        if (h == null) {
            return new Node(key,null,null,true);
        }

        //BST Insertion
        if (key < h.key) {
            h.left = insertHelper(h.left, key);
        } else if (key > h.key) {
            h.right = insertHelper(h.right, key);
        } else {
            return h;
        }

        //Case One: Right child is red, and left child is black : rotate left
        //fix right leaning red links
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        //Case Two: Two consecutive red links on the left: rotate right
        // Fix red-red violation
        if (isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }

        //Case Three: Both children are red , so color flip
        //Temporarily merges into a 4-node, to be split into a upper level
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }

    /**
     * Test whether the provided key is in the tree.
     * @return true if the key is in the tree, false if the key is not in the tree
     */
    public boolean find(int key){
        return findHelper(root, key);
    }

    //recurive helper method for search, standard binary search tree algorithm
    private boolean findHelper(Node node, int key) {
        //base case, reached null
        if (node == null) {
            return false; 
        }
        //found the key
        if (key == node.key) {
            return true;
        }
        //recur the left subtree if the key is smaller
        else if(key < node.key) {
            return findHelper(node.left, key);
        }
        //recur to right subtree if key is larger
        else {
            return findHelper(node.right, key);
        }
    }

    /** 
     * @return The root of the tree
     * (A real implementation would not expose the root to the outside world like this, but we need
     * this feature for validation)
     * 
     * IMPORTANT: If this method does not work correctly, it will be impossible to mark your implementation.
     */
    public RedBlackNode getRoot(){
        return root;
    }

    Node root;
}