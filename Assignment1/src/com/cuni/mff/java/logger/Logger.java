package com.cuni.mff.java.logger;

import com.cuni.mff.java.collection.MyCollection;
import com.cuni.mff.java.collection.LogCollectionArray;
import com.cuni.mff.java.printer.Printer;

public class Logger {
    Printer currentPrinter = null;
    int currentLevel = 0;
   // MyCollection storage = new LogCollectionLinkedList();
    MyCollection storage = new LogCollectionArray();

    public void addPrinter(Printer p) {
        storage.add(p);

        //this.currentPrinter = p;
    }
    public void log(int level,String msg){
        if( storage.getSize() == 0){
            throw new NullPointerException("No printer is set for the logger!");
        }
        if(level >= this.currentLevel) {

            for (Object printer : storage) {
                ((Printer) printer).print(msg);
            }
        }
        /*
        if(level >= this.currentLevel) {
            for (int i = 0; i < storage.getSize(); i++) {
                Printer pr = (Printer) storage.get(i);
                pr.print(msg);
            }
        }*/
            //currentPrinter.print(msg);


    }
    public void removeElement(int i)
    {
        this.storage.remove(i);
    }
    public void removeElement(Object o)
    {
        this.storage.remove(o);
    }
    public void setCurrentLevel(int level){
        this.currentLevel = level;
    }
}
