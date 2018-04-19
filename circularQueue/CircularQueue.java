/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circularQueue;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author manishjoshi394
 */
public class CircularQueue<Item> implements Iterable<Item>
{
    Item arr[] = (Item[]) new Object[1];
    int front = -1, rear = -1;
    int size = 0;
    void push(Item s)
    {
        if (rear == -1)
            front = rear = 0;
        else if ((rear == (arr.length - 1) && front == 0) || front == 1 + rear) {
            resize(2 * arr.length);
            ++rear;
        }         
        else if (rear == arr.length - 1)
            rear = 0;
        else rear++;
        
        ++size;
        arr[rear] = s;
    }
    Item pop()
    {
        Item s;
        if (front == -1)
            throw new java.lang.NoSuchMethodError("Underflow");
        else
        {
            s = arr[front];
            arr[front] = null;
            if (rear == front)
                rear = front = -1;
            else if (front == arr.length - 1)
                front = 0;
            else front++;
        }
        --size;
        if (size == arr.length / 4 && size > 0)
            resize(arr.length / 2);
        return s;
    }
    
    void resize(int capacity)
    {
        
        Item[] newArr = (Item[]) new Object[capacity];
        int i;
        int k = front;
        for(i = 0; i < size; ++i)
        {
            if(k >= arr.length)      
            {
               k = 0; 
            }
            newArr[i] = arr[k++];
        }               
        
        arr = newArr;
        front = 0;
        rear = size - 1;
    }
    
    @Override
    public Iterator<Item> iterator()
    {
        return new QueueIterator();
    }
    
    class QueueIterator implements Iterator<Item>
    {
        int i = front - 1;

        @Override
        public boolean hasNext() {
            return i != rear;
        }

        @Override
        public Item next() {
            if(i == arr.length - 1)
                i = 0;
            else ++i;
            return arr[i];
            
        }
        
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner("t - t ");
        
        CircularQueue<String> stack;
        stack = new CircularQueue<>();
        while (sc.hasNext())
        {
            String s = sc.next();
            if (s.equals("-"))
                System.out.print(stack.pop() + " ");
            else
                stack.push(s);
        }
        for(String x : stack)
            System.out.print(x + " ");
    }
    
}
