package com.cuni.lab2.MyCollection;

import java.util.Objects;

public class MyCollection<T> implements MyCollectionInterface<T> {
    private T[] myItems;
    public MyCollection() {
        myItems = (T[])new Objects[100];

    }

    @Override
    public void add(T o) {

    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public void remove(T o) {

    }

    @Override
    public void remove(int index) {

    }
}
