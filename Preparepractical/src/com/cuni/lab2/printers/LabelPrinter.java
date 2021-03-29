package com.cuni.lab2.printers;

public class LabelPrinter extends GenericPrinter{

    private String thisLabel;
    public LabelPrinter()
    {
        this("Empty label");
    }
    public LabelPrinter(String label) {
        System.out.println("Label printer!");
        thisLabel = label;
    }

    @Override
    public void print(String msg) {
        System.out.println(thisLabel + ": " +msg);
    }
}
