# LLRB-Tree-Implementation
A complete implementation of a Left-Leaning Red-Black Tree (LLRB) data structure in Java, providing efficient insertion and search operations with guaranteed logarithmic time complexity.

## Overview

This project implements a self-balancing binary search tree that maintains balance through color properties and rotation operations. Red-Black Trees are widely used in production systems, including Java's `TreeMap` and C++'s `std::map`, due to their excellent worst-case performance guarantees.

## Features

- **Insertion**: Add elements while maintaining red-black tree properties
- **Search**: Efficient lookup of elements in O(log n) time
- **Self-Balancing**: Automatic tree rebalancing through rotations and color flipping
- **Left-Leaning Variant**: Simplified implementation using the LLRB approach

## Red-Black Tree Properties

The implementation maintains the following invariants:

1. Every node is either red or black
2. The root is always black
3. All null children are considered black
4. Red nodes cannot have red children (no consecutive red links)
5. Every path from root to null has the same number of black nodes
6. Red links lean left (LLRB-specific property)

## Implementation Details

### Core Operations

#### Insert Operation
```java
public boolean insert(int key)
```
- Returns `true` if the key was successfully inserted
- Returns `false` if the key already exists in the tree
- Time Complexity: **O(log n)**
- Automatically rebalances the tree after insertion

#### Find Operation
```java
public boolean find(int key)
```
- Returns `true` if the key exists in the tree
- Returns `false` if the key is not found
- Time Complexity: **O(log n)**

### Balancing Mechanisms

The implementation uses three key operations to maintain balance:

1. **Left Rotation**: Corrects right-leaning red links
   - Time Complexity: O(1)
   
2. **Right Rotation**: Fixes consecutive red links on the left
   - Time Complexity: O(1)
   
3. **Color Flip**: Splits 4-nodes by flipping colors
   - Changes parent to red and both children to black

## Algorithm Workflow

The insertion process follows these steps:

1. Perform standard BST insertion (create new red node at leaf)
2. Apply fix-up operations while unwinding recursion:
   - If right child is red and left child is black → rotate left
   - If left child and left-left grandchild are both red → rotate right
   - If both children are red → flip colors
3. Ensure root is black

## Usage Example

```java
RedBlackTree tree = new RedBlackTree();

// Insert elements
tree.insert(10);  // Returns true
tree.insert(20);  // Returns true
tree.insert(15);  // Returns true
tree.insert(10);  // Returns false (already exists)

// Search for elements
tree.find(15);    // Returns true
tree.find(25);    // Returns false

// Get the root node
RedBlackNode root = tree.getRoot();
```

## Time Complexity Analysis

| Operation | Average Case | Worst Case |
|-----------|--------------|------------|
| Insert    | O(log n)     | O(log n)   |
| Search    | O(log n)     | O(log n)   |
| Rotation  | O(1)         | O(1)       |
| Color Flip| O(1)         | O(1)       |

## Space Complexity

- **O(n)** for storing n nodes
- Each node stores: key (int), two child pointers, and color (boolean)

## File Structure

```
.
├── RedBlackTree.java       # Main implementation
└── README.md              # This file
```

## Interface Implementation

The `Node` class implements the `RedBlackNode` interface with the following methods:

- `int key()` - Returns the key stored in the node
- `boolean isRed()` - Returns true if the node is red
- `RedBlackNode leftChild()` - Returns the left child
- `RedBlackNode rightChild()` - Returns the right child

## Technical Highlights

- **No parent pointers**: Uses recursive implementation for cleaner code
- **Top-down insertion**: Fixes violations during recursion unwinding
- **Left-leaning constraint**: Simplifies code by eliminating symmetric cases
- **Minimal color flips**: Only flips colors when both children are red

## Advantages of LLRB Trees

Compared to standard red-black trees:

1. **Simpler code**: ~50% less code than traditional implementations
2. **Easier to understand**: One-to-one correspondence with 2-3 trees
3. **Fewer cases**: Left-leaning property reduces rotation scenarios
4. **Same performance**: Maintains O(log n) guarantees

## Testing

The implementation includes:
- Duplicate key rejection
- Automatic balancing verification through rotations
- Root color enforcement (always black)
- BST property maintenance

## Limitations

Current implementation does not include:
- Deletion operations
- Tree traversal methods (inorder, preorder, postorder)
- Size/height tracking
- Iterator support

## References

- Robert Sedgewick's Left-Leaning Red-Black Trees
- *Introduction to Algorithms* by Cormen, Leiserson, Rivest, and Stein
- *Algorithms, 4th Edition* by Robert Sedgewick and Kevin Wayne

## License

This project is an academic assignment for CSC 225.


