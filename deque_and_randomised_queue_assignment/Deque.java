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


import java.util.Iterator;


/**
 * Deque is double ended queue that supports pop operation from both front and rear.
 * Deque is using Doubly linked list list to have O(1) for remove back operation.
 * @author manishjoshi394
 * @param <Item> type
 */
public class Deque<Item> implements Iterable<Item> 
{
   
    private Node<Item> front, rear;
    
    private int size;
    private class Node<Item>
    {
        Item data;
        Node<Item> next;   // using a doubly linked list for constant time back delete operation
        Node<Item> previous;
        Node(Item s)
        {
            data = s;
            next = null;
            previous = null;
        }
        
    } 
    
    public Deque()                           // construct an empty deque
    {
         size = 0;
         front = null;
         rear = null;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return rear == null;
    }
    public int size()                        // return the number of items on the deque
    {
        return size;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null)
            throw new java.lang.IllegalArgumentException("Null not allowed in deque");
        
        Node<Item> newNode = new Node<>(item);
        if (front == null)
        {
            front = newNode;
            rear = newNode;
        }
        else 
        {
            newNode.next = front;
            front.previous = newNode;
            front = newNode;
        }
        ++size;
    }
    public void addLast(Item item)           // add the item to the end
    {
        if (item == null)
            throw new java.lang.IllegalArgumentException("Null not allowed in deque");
        
        Node<Item> newNode = new Node<>(item);
        if (rear == null)
        {
            rear = newNode;
            front = newNode;
        }
        else
        {
            rear.next = newNode;
            newNode.previous = rear;
            rear = newNode;
        }
        ++size;
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        if (front == null)
            throw new java.util.NoSuchElementException("Underflow !!!  thrown from front");
        Item s = front.data;
        if (rear == front)
        {
            rear = null;
            front = null;
        }
        
        else {
            front = front.next;
            front.previous = null;
        }
        --size;
        return s;
        
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if (rear == null)
            throw new java.util.NoSuchElementException("Underflow !! from back");
        Item s = rear.data;
        if (front == rear)
        {
            front = null;
            rear =  null;
        }
        else {
            rear = rear.previous;
            rear.next = null;
        }
        --size;
        return s;
    }
    
    @Override
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }
    
    /**
     * @param 
     */
    private class DequeIterator implements Iterator<Item>
    {
        Node<Item> temp = front;
        @Override   // Java uses type erasure
        public boolean hasNext() {
            return temp != null;
        }

        @Override
        public Item next() {
            if (!this.hasNext())
                throw new java.util.NoSuchElementException("No next available");
            Item s = temp.data;
            temp = temp.next;
            return s;
        }
        
        @Override
        public void remove() {
        throw new UnsupportedOperationException("remove");
    }

        
    }
            
            
            
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // TODO code application logic here
       /* Scanner sc = new Scanner("10 - 20");
        Deque<Integer> stack;
        stack = new Deque<>();
        while (sc.hasNext())
        {
            String s = sc.next();
            if (s.equals("-"))
                System.out.print(stack.removeLast() + " ");
            else
                stack.addLast(Integer.parseInt(s));
        }
*/
    }
   
}
