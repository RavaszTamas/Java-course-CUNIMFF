package com.cuni.mff.java.printer;

public class LabelPrinter extends GenericPrinter {

    private String label;
    LabelPrinter()
    {
        this("default");
    }
    public LabelPrinter(String labelParam)
    {
        System.out.println("Creating LabelPrinter");
        this.label = labelParam;


    }
    @Override
    public void print(String msg)
    {

        //System.out.println(this.label + ": " + msg);
        super.print(this.label + ": " + msg);
    }


    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof LabelPrinter)) {
            return false;
        }
        LabelPrinter l = (LabelPrinter) obj;
        if(l.label == this.label)
            return true;
        else
            return false;
    }


}
