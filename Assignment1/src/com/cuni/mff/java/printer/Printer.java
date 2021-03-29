package com.cuni.mff.java.printer;

public interface Printer {
    default void print(int number)
    {
        this.print(Integer.toString(number));
    }
     void print(String msg);
}
