/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary_heap_PQ;

import java.util.Iterator;
import java.util.Scanner;

/**
 * The following Minimum oriented Priority Queue uses a simple binary heap data 
 * structure to achieve a decent performance in insert and delete operations
 * @author Manish Joshi
 * @param <Key> The type which Priority Queue is a collection of ..
 */
public class MaxPQ<Key extends Comparable<Key>>  implements Iterable<Key>
{
    private int N;
    private Key[] pq;
    
    MaxPQ()
    {
        pq = (Key[]) new Comparable[2]; // we are not going to use 0th position of the queue hence starting with array of length 2
        N = 0;
    }
    
    // Priority Queue ADT API implementation
    public void insert(Key v)
    {
        if (v == null)  throw new java.lang.IllegalArgumentException();
        if (N == pq.length - 1) resize(2 * pq.length);
        
        pq[++N] = v;
        swim(N); // promote to the level of incompetence in the Heap
    }
    public Key delMax()
    {
        if (N == 0)
            throw new java.util.NoSuchElementException("Under Flow");
        Key max = pq[1];
        exch(1, N);
        pq[N--] = null; // avoid loitering while deleting the node at the 
        // bottom (which was initially the element at top)
        return max;
    }
    public boolean isEmpty()
    {
        return N == 0;
    }
    

// Private helper functions    
    private void swim(int k)
    {
        while (k > 1)
        {
            if (!less(k / 2, k))    break;
            
            exch(k / 2, k);
            k /= 2; // parent of node k is at k / 2 where / is integer divide
        }
    }
    private void sink(int k)
    {
        while (k <= N / 2)
        {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j = j + 1;
            
            if (!less(k, j))    break;
            
            exch(k, j);
            k = j;
        }
    }
    
    
    // trivial helpers
    private void resize(int capacity)
    {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= N; ++i)
            temp[i] = pq[i];
        pq = temp;
    }
    private boolean less(int x, int y)
    {
        return pq[x].compareTo(pq[y]) < 0;
    }
    private void exch(int a, int b)
    {
        Key swap = pq[a];
        pq[a] = pq[b];
        pq[b] = swap;
    }
    
    // Iterator implementation to make the collection iterable
    @Override
    public Iterator<Key> iterator()
    {
        return new PQIterator();
    }
    
    class PQIterator implements Iterator<Key>
    {
        int i = 0;
        
        @Override
        public boolean hasNext()
        {
            return i < N; 
        }
        
        @Override
        public Key next()
        {
            return pq[++i];
        }
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner("t h { t -10 h 0 a b c d -10 t j 700");
        
        MaxPQ<String> stack;
        stack = new MaxPQ<>();
        while (sc.hasNext())
        {
            String s = sc.next();
            if (s.equals("-"))
                System.out.print(stack.delMax() + " ");
            else
                stack.insert(s);
        }
        int i = 0;
        System.out.println("{".compareTo("t"));
        for(String x : stack)
        {
            if (i++ % 2 == 1)
            {
                System.out.print("\n");
                for (int j = i ; j >= 0; --j)
                    System.out.print("  ");
            }
            
            System.out.print(x + " ");
        }
    }    
}
