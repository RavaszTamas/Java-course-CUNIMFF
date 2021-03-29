package com.cuni.mff.java.collection;

import com.cuni.mff.java.printer.Printer;

import java.util.Arrays;
import java.util.Iterator;

public class LogCollectionArray implements MyCollection {


    Printer[] elements = new Printer[16];
    int size = 0;
    @Override
    public void add(Object o) {
        if(size == elements.length){
            this.resize();
        }
        this.elements[this.size] = (Printer) o;
        this.size++;
    }
    private void resize()
    {
        elements = Arrays.copyOf(elements,elements.length*2);
    }

    @Override
    public Object get(int i) {
        if(i >= elements.length)
            throw new IndexOutOfBoundsException("On that position there was no existing item");
        return elements[i];
    }

    @Override
    public void remove(Object o) {
        int index = 0;
        while (index < this.size)
        {
            if (this.elements[index].equals(o))
                {
                    for(int indexIn = index;indexIn < this.size-1;indexIn++)
                        elements[indexIn] = elements[indexIn+1];
                    this.size--;

                }
            else
                index++;
        }
    }

    @Override
    public void remove(int i) {
        if (i >= elements.length)
            throw new IndexOutOfBoundsException("On that position there was no existing item");
        for (int idx = i; idx < this.size - 1; idx++){
            elements[idx] = elements[idx + 1];
        }
        this.size--;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Object next() {
                return elements[index++];
            }
            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }
}
