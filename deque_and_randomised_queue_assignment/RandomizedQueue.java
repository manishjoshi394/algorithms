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
package deque_and_randomised_queue_assignment;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

/**
 * A randomized queue is similar to a normal queue except the fact that it pops out items randomly.
 * Following implementation is resizing array implementation of a Random Queue 
 * and hence it takes linear time ~ 3N in Amortized analysis for adding N items to the queue.
 * Worst case case time is not constant for adding one item. 
 * However average performance is linear for adding linear items. 
 * (See lectures on Coursera course of Algorithms 1 by Princeton University)
 * @author manishjoshi394
 * @param <Item> Type of data stored in Queue
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] arr;
    private int rand, rear;
    
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        arr = (Item[]) new Object[1];
        rand = -1;
        rear = -1;
        
    }
    
    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return rear == -1;
    }
    public int size()                        // return the number of items on the randomized queue
    {
        return rear + 1;
    }
    public void enqueue(Item item)           // add the item
    { 
        if (item == null)
            throw new java.lang.IllegalArgumentException("Null not allowed !");
        
        if (rear == arr.length - 1)
            resize(2 * arr.length);
        
        
        arr[++rear] = item;
        
    }
    
    private void resize(int capacity)
    {
        Item[] newArr = (Item[]) new Object[capacity];
        
        for (int i = 0; i <= rear; ++i)
        {
            newArr[i] = arr[i];   
        }
        arr = newArr;

    }
    
    public Item dequeue()                    // remove and return a random item
    {
        if (rear == -1)
            throw new java.util.NoSuchElementException("Underflow");
        rand = StdRandom.uniform(0, rear + 1);
        Item s = arr[rand];
        arr[rand] = arr[rear];   // Copy rear element to rand and remove rear element.. this way we avoid having array wit holes
        arr[rear--] = null; // Avoid loitering
        
        if (rear + 1 == arr.length / 4 && rear > 0)
            resize(arr.length / 2);          // freeing up memory if queue gets sufficiencientlyh empty while avoiding thrashing at the same time
        
        return s;
    }
    public Item sample()                     // return a random item (but do not remove it)
    {
        if (rear == -1)
            throw new java.util.NoSuchElementException("underflow!");
        rand = StdRandom.uniform(0, rear + 1);
        return arr[rand];
    }
    @Override
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return  new RndQueueIterator();
    }
    private class RndQueueIterator implements Iterator<Item>
    {
        int rand, rear;
        Item[] arr;
        
        public RndQueueIterator() {
            rear = RandomizedQueue.this.rear;
            arr = (Item[]) new Object[rear + 1]; 
            for (int i = 0; i < arr.length; ++i)
                arr[i] = RandomizedQueue.this.arr[i];
        }
        
        
        @Override
        public boolean hasNext() {
            return rear >= 0;
        }

        @Override
        public Item next() {
            if (!this.hasNext())
                throw new java.util.NoSuchElementException("No next available");
            
            rand = StdRandom.uniform(0, rear + 1);
            Item s = arr[rand]; 
            arr[rand] = arr[rear];
            arr[rear--] = null;
            return s;
        }
        
    }
    public static void main(String[] args)   // unit testing (optional)
    {
        RandomizedQueue<Integer> stack;
        stack = new RandomizedQueue<>();
        stack.dequeue();
        /* Scanner sc = new Scanner("25 - 30");
        RandomizedQueue<Integer> stack;
        stack = new RandomizedQueue<>();
        while (sc.hasNext())
        {
            String s = sc.next();
            if (s.equals("-"))
                System.out.print(stack.dequeue() + " ");
            else
                stack.enqueue(Integer.parseInt(s));
        }
        
        for (Integer x : stack) // Implementing an iterable and iterator interafaces helps us write this much clear and compact code so that it looks like systantic sugar which is baews on Type Erasure in Java 
        {
            System.out.print(x + " ");
        }
        */
    }

    
}
