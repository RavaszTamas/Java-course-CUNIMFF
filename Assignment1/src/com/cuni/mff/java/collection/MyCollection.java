package com.cuni.mff.java.collection;

public interface MyCollection extends Iterable{
    void add(Object o);
    Object get(int i);
    void remove(Object o);
    void remove(int i);
    int getSize();
}
