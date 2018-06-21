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
 * THis class is a typical binary search tree implementation of the Symbol table 
 * ADT
 * @author Manish Joshi
 */
public class BST<Key extends Comparable<Key>, Value> 
{
    private Node root;
    
    private class Node
    {
        Key key;
        Value value;
        
        Node left;
        Node right;
        
        int size;
        
        Node(Key key, Value value, int size)
        {
            this.key = key;
            this.value = value;
            this.size = size;
            
            left = null;
            right = null;
        }
    }
    
    /**
     * Client get() method
     * @param key the Key to be searched
     * @return the value associated with the key
     */
    public Value get(Key key)
    {
        return get(root, key);
    }
    
    private Value get(Node x, Key key)
    {
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)    return get(x.left, key);
        else if (cmp > 0)    return get(x.right, key);
        else return x.value;
    }
    
    public void put(Key key, Value value)
    {
        root = put(root, key, value);
    }
    
    private Node put(Node x, Key key, Value value)
    {
        if (x == null)  return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)    x.left = put(x.left, key, value);
        else if (cmp > 0)   x.right = put(x.right, key, value);
        else    x.value = value;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    public void delMin()    
    {
        root = delMin(root);
    }
    
    /**
     * recursive delMin implementation
     * @param x current Node under inspection 
     * @return 
     */
    private Node delMin(Node x)
    {
        if (x.left == null)  return x.right;
        x.left = delMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    public void delMax()
    {
        root = delMax(root); // call the delMax with root and update the root link
    }
    
    /**
     * Recursive delmax implementation
     * @param x root of the current subtree in which max is to be deleted
     * @return the root of the current subtree 
     */
    private Node delMax(Node x)
    {
        if (x.right == null)    return x.left;
        x.right = delMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    /**
     * Implementation of the elementary Hibbard deletion technique
     * The method is inefficient of large deletion cases as the performance 
     * degrades to square of N order because the tree tends to be unbalanced 
     * after huge number of insertions and deletions
     * @param key The key to be deleted
     */
    public void delete(Key key)
    {
         root = delete(root, key);
    }
    
    public Node delete(Node x, Key key)
    {
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)    x.left = delete(x.left, key);
        else if (cmp > 0)   x.right = delete(x.right, key);
        else
        {
            if (x.left == null) return x.right;
            if (x.right == null)    return x.left;
            
            Node t = x;
            x = min(t.right);
            x.right = delMin(t.right);
            x.left = t.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    public int size()
    {
        return size(root);
    }
    
    private int size(Node x)
    {
        if (x == null)  return 0;
        return x.size;
    }
    
    public Key select(int k)
    {
        Node x = select(root, k);
        if (x == null)  return null;
        return x.key;
    }
    
    public Node select(Node x, int k)
    {
        // return Node containing key of rank k
        if (x == null)  return null;
        int t = size(x);
        if (k < t)  return select(x.left, k);
        else if (k > t) return select(x.right, k - t - 1);
        else    return x;
    }
    
    public Key min()
    {
        return min(root).key;
    }
    
    private Node min(Node x)
    {
        if (x.left == null) return x;
        return min(x.left);
    }
    
    public Key max()
    {
        return max(root).key;
    }
    
    private Node max(Node x)
    {
        if (x.right == null)    return x;
        return max(x.right);
    }
    
    public Key floor(Key key)
    {
        Node x = floor(root, key);
        if (x == null)  return null;
        return x.key;
    }
    
    /**
     * Recursive floor implementation 
     * @param x the root node of the tree in which floor is to be found
     * @param key the key of which floor is to be found 
     * @return the Node with largest key smaller than provided key
     */
    private Node floor(Node x, Key key)
    {
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)   return x;
        else if (cmp < 0)   return floor(x.left, key);
        
        Node temp = floor(x.right, key);
        if (temp != null)   return temp;
        return x;
    }
    
    // The ceiling fuction 
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
        
        Node temp = ceiling(x.left, key);
        if (temp == null)   return x;
        return temp;
    }
    
    // Implementation of the rank operation
    public int rank(Key key)
    {
        return rank(root, key);
    }
    
    private int rank(Node x, Key key)
    {
        if (x == null)   return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)    return rank(x.left, key);
        else if (cmp > 0)   return 1 + size(x.left) + rank(x.right, key);
        else    return size(x.left);
    }
    
    // Inorder traversal 
    public Iterable<Key> keys()
    {
        Queue<Key> q = new LinkedList<>();
        inOrder(root, q);
        return q; // return the iterable queue
    }
    
    /**
     * Recursive implementation of the inOrder traversal of the BST
     * The function checks if the tree is null, returns if it is
     * calls itself for the left subtree
     * enqueues the root of the current subtree
     * and then calls itself for the right subtree.
     * 
     * @param x the root of the current subtree
     * @param q The queue to which the Keys are to be added
     */
    private void inOrder(Node x, Queue q)
    {
        if (x == null)  return;
        inOrder(x.left, q);
        q.add(x.key);
        inOrder(x.right, q);
    }
    
    public static void main(String[] args) {
        BST<Integer, String> table = new BST<>();
        table.put(0, "My");
        table.put(1, "Name");
        table.put(2, "is");
        table.put(0, "MJ");
        table.put(5, "devdas");
        table.put(10, "for");
        table.put(14, "Deepika");
        table.put(14, "Bhawna");
        table.delete(1);
        for (Integer x : table.keys())
            System.out.println(table.get(x));
        
        //table.delete(1);
        
        //TODO code application logic here
    }
}
