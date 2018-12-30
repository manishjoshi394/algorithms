/*
 * Copyright 2018 Manish Joshi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dictionary_n_binary_search_trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Manish Joshi
 * @param <Key>
 * @param <Value>
 */
public class AVLTreeST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {

        Key key;
        Value value;
        int size;       // stores size of the subtree rooted at this Node
        int height;     // stores the height of subtree rooted at this node

        Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }
    
    /**
     * ******************************************************
     * API Implementation starts here
     * *****************************************************
     */
    /**
     * Returns the number of Key-Value pairs of the AVL tree.
     *
     * @return the number of Key-Value pairs of the AVL tree
     */
    public int size() {
        return size(root);
    }

    // helper method to calculate size of the subtree
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    // helper for height balancing
    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return x.height;
    }

    // helper to calculate balance factor for a Node
    private int balanceFactor(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x.left) - height(x.right);
    }

    /**
     * Returns the value associated will the given Key, returns null otherwise.
     *
     * @param key
     * @return the value associated will the given Key, returns null otherwise
     */
    public Value get(Key key) {
        return get(root, key);
    }

    // helper function for get function
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    /**
     * Inserts the given Key, Value pair into the tree.
     *
     * @param key The key to be inserted
     * @param value the associated value with the key
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
        
        assert check();
    }

    // helper function for putting into the tree.
    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return balance(x);
    }

    // balances the tree and returns the pointer to the new root node
    private Node balance(Node x) {

        // Either LL or LR case
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);    // Half the work for LR case
            }
            x = rotateRight(x);
        } // Either RR or RL case
        else if (balanceFactor(x) < -1) {

            if (balanceFactor(x.right) > 0) {
                x.right  = rotateLeft(x.right); // convert RL to RR
            }
            x = rotateLeft(x);
        }
        return x;
    }

    /**
     * Returns the minimum key present in the AVL Tree.
     *
     * @return the minimum key present in the AVL Tree
     */
    public Key min() {
        Node x = root;
        if (x == null) {
            return null;
        }
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    /**
     * Returns the maximum key in the AVL Tree.
     *
     * @return the maximum key in the AVL Tree
     */
    public Key max() {
        Node x = root;
        if (x == null) {
            return null;
        }
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    /**
     * Returns the ceil of the given Key present in the AVL Tree.
     *
     * @param key
     * @return the ceil of the given Key present in the AVL Tree
     */
    public Key ceiling(Key key) {
        Node t = ceiling(root, key);
        if (t == null) {
            return null;
        }
        return t.key;
    }

    // helper function for getting the ceiling of key
    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t == null) {
                return x;
            } else {
                return t;
            }
        } else if (cmp > 0) {
            return ceiling(x.right, key);
        } else {
            return x;
        }
    }

    /**
     * Returns the floor of the given key from the tree.
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        Node t = floor(root, key);
        if (t == null) {
            return null;
        }
        return t.key;
    }

    // helper function for implementing the floor function
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, key);
        } else if (cmp > 0) {
            Node t = floor(x.right, key);
            if (t == null) {
                return x;
            } else {
                return t;
            }
        } else {
            return x;
        }
    }

    /**
     * Returns the rank of the key(number of key smaller than the given key).
     *
     * @param key
     * @return the rank of the key
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    // helper function for implementing rank function
    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(x.right, key);
        } else {
            return size(x.left);
        }
    }

    /**
     * Deletes the key value pair from the tree.
     *
     * @param key
     */
    public void delete(Key key) {
        root = delete(root, key);
        
        assert check();
    }

    // helper for deletion
    private Node delMin(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.right;
        }
        x.left = delMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    // helper for deletion
    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    // implements hibbard deletion
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.left == null) {
                return x.right;
            } else if (x.right == null) {
                return x.left;
            }
            Node t = x;
            x = min(t.right);
            x.right = delMin(t.right);
            x.left = t.left;
        }

        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        
        return balance(x);
    }

    private void inOrder(Node x, Queue<Key> q) {
        if (x == null) {
            return;
        }
        inOrder(x.left, q);
        q.add(x.key);
        inOrder(x.right, q);
    }

    /**
     * Returns preorder traversal trace of the tree.
     *
     * @return preOrder traversal trace of the tree
     */
    public Iterable<Key> preOrder() {
        Queue<Key> q = new LinkedList<>();
        preOrder(q, root);
        return q;
    }

    private void preOrder(Queue<Key> q, Node x) {
        if (x == null) {
            return;
        }
        q.add(x.key);
        preOrder(q, x.left);
        preOrder(q, x.right);
    }

    /**
     * Returns all Keys in the Tree.
     */
    Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();
        inOrder(root, q);
        return q;
    }

    // left rotates the right child of x
    private Node rotateLeft(Node x) {
        if (x.right == null) {
            return x;
        }
        Node t = x.right;
        x.right = t.left;
        t.left = x;
        t.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return t;
    }

    // right rotates the left child of x
    private Node rotateRight(Node x) {
        if (x.left == null) {
            return x;
        }
        Node t = x.left;
        x.left = t.right;
        t.right = x;
        t.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return t;
    }
    
    // checks the tree for discrepancies
    private boolean check() {
        if (!isAVL()) {
            System.out.println("Tree is NOT AVL");
        }
        if (!isBST()) {
            System.out.println("Tree is NOT BST");
        }
        return isAVL() && isBST();
    }
    
    // checks whether the tree is AVL or not
    private boolean isAVL() {
        return isAVL(root);
    }
    
    // helper for checking AVL property
    private boolean isAVL(Node x) {
        if (x == null) {
            return true;
        }
        boolean xIsValid = (balanceFactor(x) >= -1 && balanceFactor(x) <= 1);
        if (!xIsValid) {
            return false;
        }
        return isAVL(x.left) && isAVL(x.right) && xIsValid;
    }

    // checks whether the tree satisfies BST property or not
    private boolean isBST() {
        return isBST(root, null, null);
    }
    
    private boolean isBST(Node x, Key l, Key r) {
        if (x == null) {
            return true;
        }
        if (l != null && x.key.compareTo(l) <= 0) {
            return false;
        } 
        if (r != null && x.key.compareTo(r) >= 0) {
            return false;
        }
        return isBST(x.left, l, x.key) && isBST(x.right, x.key, r);
    }
    
    // TODO : Implement range queries and select operation
    public static void main(String[] args) {
        AVLTreeST<Integer, String> st = new AVLTreeST<>();
        st.put(2, "Manish");
        st.put(3, "Joshi");
        st.put(1, "Deepika");
        st.put(4, "Love");
        
        //st.delete(2);
        System.out.println(st.rank(6));
        for (Integer x : st.keys()) {
            System.out.println(x + " " + st.get(x));
        }
    }
}
