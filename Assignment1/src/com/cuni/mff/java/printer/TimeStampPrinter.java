package com.cuni.mff.java.printer;

public class TimeStampPrinter extends GenericPrinter {
    public TimeStampPrinter()
    {
        System.out.println("Creating TimeStampPrinter");
    }
    @Override
    public void print(String msg){
        System.out.println((new java.util.Date()) + " "+ msg);
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof TimeStampPrinter)) {
            return false;
        }
        return true;
    }
}
