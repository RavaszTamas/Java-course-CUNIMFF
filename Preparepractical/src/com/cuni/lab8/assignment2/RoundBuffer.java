package com.cuni.lab8.assignment2;

public class RoundBuffer {
    private int CAPACITY;
    private StringBuffer buffer;
    RoundBuffer()
    {
        this(1000);
    }
    RoundBuffer(int capacity)
    {
        CAPACITY = capacity;
        buffer = new StringBuffer(CAPACITY);
    }

    synchronized void put(String msg) throws InterruptedException {
        while (buffer.length() + msg.length() > CAPACITY)
            wait();
        buffer.append(msg);
        notify();
    }
    synchronized String get() throws InterruptedException
    {
        while (buffer.length()  == 0)wait();

        String toReturn = buffer.toString();
        buffer.delete(0,buffer.length());
        return toReturn;

    }
}
