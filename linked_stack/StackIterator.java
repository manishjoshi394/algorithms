/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linked_stack;

import java.util.Iterator;


public class StackIterator<Item> extends LinkedStack<Item> implements Iterator<Item> {
    
    Node temp = top;
    @Override
    public boolean hasNext() {
        return temp != null;
    }

    @Override
    public Item next() {
        Item s = temp.data;
        temp = temp.next;
        return s;
    }
    
}
