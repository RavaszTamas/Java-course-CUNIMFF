package com.cuni.lab2.printers;

public class TimeStampPrinter extends GenericPrinter {
    public TimeStampPrinter() {
        System.out.println("Timestamp printer!");
    }

    @Override
    public void print(String msg) {
        System.out.println((new java.util.Date())+ ": "+msg);
    }
}
