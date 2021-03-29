package com.cuni;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

class myException extends Exception{
    myException(String message)
    {
        super(message);
    }
}

public class Main {

    public static void validateInput(int month, int year) throws myException {
        if(month < 1 || month > 12)
            throw new myException("Invalid month!");
    }

    public static void main(String[] args) {
        //Calendar cal = Calendar.getInstance();

        if(args.length != 2)
        {
            System.out.println("Invalid number of arguments!");
            return;
        }
        HashMap<Integer,String> days = new HashMap<>();
        days.put(1,"Mo");
        days.put(2,"Tu");
        days.put(3,"We");
        days.put(4,"Th");
        days.put(5,"Fr");
        days.put(6,"Sa");
        days.put(7,"Su");
        int month= 0;
        int year = 0;
        try
        {
            month = Integer.parseInt(args[0]);
            year = Integer.parseInt(args[1]);
            validateInput(month,year);
        }
        catch (myException| NumberFormatException ex)
        {
            System.out.println("Invalid month or year");
            return;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(year,month-1,1);
        //System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("Su " + "Mo " + "Tu " + "We " + "Th " + "Fr " + "Sa");
        int length = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayOfweek = cal.get(Calendar.DAY_OF_WEEK);
        //System.out.println(length +" "+startDay);
        for(int i = 1; i < dayOfweek;i++)
        {
            System.out.print("   ");
        }
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < length; i++,dayOfMonth++,dayOfweek++)
        {
            if(dayOfMonth<10)
            {
                System.out.print(" "+dayOfMonth+" ");
            }
            else
            {
                System.out.print(dayOfMonth+" ");
            }
            if(dayOfweek==7)
            {
                System.out.println("");
                dayOfweek=0;
            }
        }
    }
}
