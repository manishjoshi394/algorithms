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
 * A recursive implementation of left leaning red black BSTs
 * @author Manish Joshi
 * @param <Key> T
 * @param <Value>
 */

public class RedBlackBST<Key extends Comparable<Key>, Value> 
{
    private static final boolean RED = true;
    private static final boolean BLACK = false; 
    private Node root;
    
    private class Node
    {
        Key key;
        Value value;
        
        Node left;
        Node right;
        
        boolean color;
        int size;
        public Node(Key key, Value value, int size, boolean color)
        {
            this.key = key;
            this.value = value;
            this.size = size; 
            this.color = color;
        }
    }
    
    // API implementation
    public int size()
    {
        return size(root);
    }
    
    private int size(Node x)
    {
        if (x == null)  return 0;
        return x.size;
    }
    
    public Value get(Key key)
    {
        Node x = root;
        while(x != null)
        {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)    x = x.left;
            else if (cmp > 0)   x = x.right;
            else    return x.value;
        }
        return null;
    }
    
    public void put(Key key, Value value)
    {
        root = put(root, key, value);
        root.color = RED;
    }
    
    /**
     * The Red Black Tree insertion algorithm
     * @see Algorithms 4th edition by Sedgewick 
     * @param x
     * @param key
     * @param value
     * @return 
     */
    private Node put(Node x, Key key, Value value)
    {
        if (x == null)  return new Node(key, value, 1, RED);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)    x.left = put(x.left, key, value);
        else if (cmp > 0)   x.right = put(x.right, key, value);
        else    x.value = value;
        
        // careful study is required to understand the following ifs cover all 
        // the node insertion cases
        if (isRed(x.right) && !isRed(x.left))   rotateLeft(x);  // rotations requied = 2
        if (isRed(x.left) && isRed(x.left.left)) rotateRight(x);    // rotations required = 1
        if (isRed(x.left) && isRed(x.right))    flipColors(x);  // rotations required = 0
        
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    public Key min()
    {
        Node x = root;
        while (x.left != null)
        {
            x = x.left;
        }
        return x.key;
    }
    
    public Key max()
    {
        Node x = root;
        while (x.right != null)
        {
            x = x.right;
        }
        return x.key;
    }
    
    public Key ceiling(Key key)
    {
        Node x = ceiling(root, key);
        if (x == null)  return null;
        return x.key;
    }
    
    private Node ceiling(Node x, Key key)
    {
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)   return x;
        else if (cmp > 0)   return ceiling(x.right, key);
        
        Node t = ceiling(x.left, key);
        if (t != null)  return t;
        return x;
    }
    
    public Key floor(Key key)
    {
        Node x = floor(root, key);
        if (x == null)  return null;
        return x.key;
    }
    
    private Node floor(Node x, Key key)
    {
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)   return x;
        else if (cmp < 0)   return floor(x.left, key);
        
        Node t = floor(x.right, key);
        if (t != null)  return t;
        return x;
    }
    
    public int rank(Key key)
    {
        return rank(root, key);
    }
    
    private int rank(Node x, Key key)
    {
        if (x == null)  return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)    return rank(x.left, key);
        else if (cmp > 0)   return 1 + size(x.left) + rank(x.right, key);
        else    return size(x.left);
    }
    
    public Iterable<Key> keys()
    {
        Queue<Key> q = new LinkedList<>();
        inOrder(root, q);
        return q;
    }
    
    private void inOrder(Node x, Queue q)
    {
        if (x == null)  return;
        inOrder(x.left, q);
        q.add(x.key);
        inOrder(x.right, q);
    }
    // utility functions
    private void flipColors(Node x)
    {
        if (x == null)  return;
        x.left.color = BLACK;
        x.right.color = BLACK;
        x.color = RED;
    }
    
    private boolean isRed(Node x)
    {
        if (x == null)  return false; // null links to be considered black
        return x.color == RED;
    }
    
    private void rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
    }
    
    private void rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
    }
}
