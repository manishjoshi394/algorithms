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
package arraystack;

import java.util.Scanner;
import java.util.Iterator;
import java.lang.Iterable;
/**
 *
 * @author manishjoshi394
 * @param <Item> the generic data type for stack implementation
  */
public class ArrayStack<Item> implements Iterable<Item> // parameterised types/data types of java called Generics
{
    
    Item[] arr = (Item[]) new Object[1];
    int top = -1;
    
    public void push(Item s)
    {
        if (top == (arr.length - 1))
            resize(2 * arr.length);
                
        
       arr[++top] = s;
    }
    
    public boolean isEmpty()
    {
        return top == -1;
    }
    
    public Item pop()
    {
        Item s = arr[top];
        arr[top--] = null; //
        
        if (top == ((arr.length / 4) - 1) && top > 0) 
        {
           resize(arr.length / 2);
        } 
        return s;
    }
    public Item peek()
    {
        if (isEmpty())
        {
            throw new java.util.NoSuchElementException("Can't peek through empty stack");
        }
        return arr[top];
    }
    private void resize(int capacity)  // resizing array implementation 
    {
        Item[] newArr = (Item[]) new Object[capacity];  // the generic types have to be reference data types
        for (int i = 0; i <= top; ++i)
        {
            newArr[i] = arr[i];
        }
        arr = newArr;
      //  System.out.println(arr.length);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner("10 - 324345 43543 534 5 345 - -");
        ArrayStack<Integer> stack;
        stack = new ArrayStack<>();
        while (sc.hasNext())
        {
            String s = sc.next();
            if (s.equals("-"))
                System.out.print(stack.pop() + " ");
            else
                stack.push(Integer.parseInt(s));
        }
        
        for (Integer x : stack) // Implementing an iterable and iterator interafaces helps us write this much clear and compact code so that it looks like systantic sugar which is baews on Type Erasure in Java 
        {
            System.out.print(x + " ");
        }
        
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator(); //To change body of generated methods, choose Tools | Templates.
    }
    class StackIterator implements Iterator<Item>
    {
        int i = top + 1;
        @Override
        public boolean hasNext() {
            
            return i > 0;  //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Item next() {
            return arr[--i]; //To change body of generated methods, choose Tools | Templates.
        }
        
    }
       
}
