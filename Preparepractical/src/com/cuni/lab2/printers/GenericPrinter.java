package com.cuni.lab2.printers;

public class GenericPrinter implements Printer {
    public GenericPrinter() {
        System.out.println("Generic printer!");
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
}
