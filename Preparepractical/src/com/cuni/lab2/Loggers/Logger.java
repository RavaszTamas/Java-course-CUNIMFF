package com.cuni.lab2.Loggers;

import com.cuni.lab2.printers.Printer;


public class Logger {

    Printer printer;
    int lvl=10;
    public Logger(){}
    void addPrinter(Printer pr)
    {
        printer = pr;
    }
    void setLevel(int plvl)
    {
        lvl = plvl;
    }
    void log(int plvl, String msg) throws LoggerException {
        if(plvl >= lvl)
        {
            if(printer != null)
                printer.print(msg);
            else
                throw new LoggerException("No logger set!");
        }
    }

}
