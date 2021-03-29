package com.cuni.mff.java.collection;

import com.cuni.mff.java.printer.Printer;

import java.util.Iterator;

class Node
{
    public Node next = null;
    public Printer currentPrinter = null;
}

public class LogCollectionLinkedList implements MyCollection {

    private Node head = null;
    public int size = 0;
    @Override
    public int getSize()
    {
        return  this.size;
    }
    @Override
    public void add(Object o) {
        Printer newPrinter = (Printer) o;
        Node newNode = new Node();
        newNode.currentPrinter = newPrinter;

        Node currentNode = this.head;
        Node prevNode = null;

        while(currentNode != null){
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        if(currentNode == null && prevNode == null){
            this.head = newNode;
        }
        else{
            prevNode.next = newNode;
        }
        this.size++;

    }
    @Override
    public Object get(int i) {
        if(i >= this.size)
            throw new IndexOutOfBoundsException("On that position there was no existing item");
        Node current = head;
        int pos = 0;
        while(pos < i) {
            current = current.next;
            pos++;
        }

        return current.currentPrinter;

    }
    @Override
    public void remove(Object o) {
        Object elementToRemove = o;
        Node current = head;
        Node prev = null;
        while (current != null && current.currentPrinter.equals(elementToRemove)) {
            prev = current;
            current = current.next;
        }
        if (current != null) {
            if(prev == null){
                this.head = current.next;
                current.next = null;
            }
            else{
                prev.next = current.next;
                current.next = null;
            }
        } else
        {
            throw new IndexOutOfBoundsException("No item of that type exists in the list!");
        }

    }
    @Override
    public void remove(int i) {
        if(i >= this.size)
            throw new IndexOutOfBoundsException("On that position there was no existing item");
        Node current = head;
        Node prev = null;
        int pos = 0;
        while(pos < i){
            prev = current;
            current = current.next;
            pos++;
        }
        if(prev == null){
            this.head = current.next;
            current.next = null;
        }
        else {
            prev.next = current.next;
        }
        this.size--;
    }

    @Override
    public Iterator iterator() {
        return  new Iterator() {
            private Node current = null;
            {
                current = head;
            }
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Object next() {
                return current = current.next;
            }
            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }
}
