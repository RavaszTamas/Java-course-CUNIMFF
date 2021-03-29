package com.cuni.lab2.MyCollection;


public interface MyCollectionInterface<T> {
    void add(T o);
    T get(int i);
    void remove(T o);
    void remove(int index);
}
