package com.cuni.mff.java.printer;

public class GenericPrinter implements Printer {
    public GenericPrinter()
    {
        System.out.println("Creating GenericPrinter");
    }


    @Override
    public void print(String msg)
    {
        System.out.println(msg);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof GenericPrinter)) {
            return false;
        }
        return  true;
    }

}