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
package linked_stack;

import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author manishjoshi394
 */
public class LinkedStack<Item> implements Iterable<Item> {

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }
    protected class Node
    {
        Item data;
        Node next = null;
        
        Node(Item arr)
        {
            data = arr;
        }
    }
    
    Node top = null;
    
    void push(Item s)
    {
        Node newNode = new Node(s);
        
       
        
        newNode.next = top;
        top = newNode;        
        
    }
    
    Item pop()
    {
        if(top == null)
            throw new IllegalAccessError("Stack Underflow");
        Item s = top.data;
        top = top.next;
        
       
        return s;
        
    }
    
    boolean isEmpty()
    {
        return top == null;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc = new Scanner("Manish is boss and baap is  also Manish - - -");
        LinkedStack<String> stack;
        stack = new LinkedStack();
        while (sc.hasNext())
        {
            String s = sc.next();
            if (s.equals("-"))
                System.out.print(stack.pop());
            else
                stack.push(s);
        }
        for(String s : stack)
            System.out.print(s + " ");
        
    }
    
}
