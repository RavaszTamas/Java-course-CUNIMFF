package com.cuni.lab2.printers;

public interface Printer {
    void print(String msg);
    default void print(int number)
    {
        print(Integer.toString(number));
    }
}
