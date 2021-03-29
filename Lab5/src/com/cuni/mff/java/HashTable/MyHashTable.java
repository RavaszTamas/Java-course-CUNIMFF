package com.cuni.mff.java.HashTable;

import java.util.Iterator;

public class MyHashTable<T,U> implements Iterable<T> {

    @FunctionalInterface
    public interface OperationInterface<U>
    {
        U apply(U value);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currPos = 0;
            private Node<T,U> current = table[currPos];
            {
                while (current == null)
                {
                    current = table[++currPos];
                }
            }
            @Override
            public boolean hasNext() {
                if(currPos == table.length-1 && current == null)
                    return false;
                return true;
            }

            @Override
            public T next() {
                T elemToReturn = null;
                if(current.nextNode == null)
                {
                    elemToReturn = current.key;
                    current = current.nextNode;
                    while (current == null && currPos < m-1)
                    {
                        current = table[++currPos];
                    }

                }
                else
                {
                    elemToReturn = current.key;
                    current = current.nextNode;
                }
                return  elemToReturn;
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }

        };
    }

    static class Node<T,U>
    {
        T key;
        U value;
        Node<T,U> nextNode;
    };
    private int m;
    private Node<T,U>[] table;
    private int myHash(T key)
    {
        int hash = key.hashCode()%m;
        if(hash < 0){
            hash += table.length;
        }
        return  hash;
    }
    public MyHashTable()
    {
        this(16);
    }
    public MyHashTable(int sizeInit) {
        m = sizeInit;
        table = new Node[sizeInit];
    }
    public U get(T key)
    {
        int pos = myHash(key);
        Node<T,U> current = table[pos];
        while (!current.key.equals(key))
        {
            current = current.nextNode;
        }

        if(current == null)
            return null;
        else
            return current.value;
    }
    public void set(T key, U value)
    {
        int pos = myHash(key);
        Node<T,U> current = table[pos];
        Node<T,U> prev = null;
        while (current != null && current.key != key)
        {
            prev = current;
            current = current.nextNode;
        }
        if(current == null)
        {
            Node<T,U> newNode = new Node<>();
            newNode.value = value;
            newNode.key = key;
            newNode.nextNode = null;

            if(prev == null)
            {
                table[pos] = newNode;
            }
            else
            {
                prev.nextNode = newNode;
            }
        }
        else
        {
            current.value = value;
        }
    }

    public void forEachValue(OperationInterface<U> operation)
    {
        for( T elem : this)
        {
            this.set(elem,operation.apply(this.get(elem)));
        }
    }

}
