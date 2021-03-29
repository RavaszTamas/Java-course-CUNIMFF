package com.cuni.mff.java.ui;

import com.cuni.mff.java.logger.Logger;
import com.cuni.mff.java.printer.GenericPrinter;
import com.cuni.mff.java.printer.LabelPrinter;
import com.cuni.mff.java.printer.Printer;
import com.cuni.mff.java.printer.TimeStampPrinter;

import javax.swing.*;
import java.util.Scanner;

public class UI {
    Logger loggerToPrint = new Logger();

    private  void addSetPrinter()
    {
        System.out.println("1: generic, 2: label, 3: timestamp:");
        Scanner inputUser = new Scanner(System.in);
        int typeOfPrinter = 0;
        while (true) {
            String inputData = inputUser.nextLine();

            try{
                typeOfPrinter = Integer.parseInt(inputData);
                if( typeOfPrinter != 1 && typeOfPrinter != 2 && typeOfPrinter != 3)
                {
                    System.out.println("Invalid printer type, try again!");
                }
                else{
                    break;
                }
            }
            catch (NumberFormatException c){
                System.out.println("Invalid numerical value try again!");
            }
        }
        Printer newPrinter;
        if(typeOfPrinter == 1 ){
            newPrinter = new GenericPrinter();
        }
        else if(typeOfPrinter == 2){
            System.out.println("Enter the label:");
            String inputData = inputUser.nextLine();
            newPrinter = new LabelPrinter(inputData);
        }
        else{
            newPrinter = new TimeStampPrinter();
        }
        loggerToPrint.addPrinter(newPrinter);

    }

    private  void setCurrentLevel()
    {
        Scanner inputUser = new Scanner(System.in);

        int newLevel = 0;
        while (true) {
            System.out.println("Enter the level");
            String inputData = inputUser.nextLine();

            try{
                newLevel = Integer.parseInt(inputData);
                break;
            }
            catch (NumberFormatException c){
                System.out.println("Invalid numerical value try again!");
            }
        }
        loggerToPrint.setCurrentLevel(newLevel);

    }

    private  void logMessage(){
        Scanner inputUser = new Scanner(System.in);
        int printLevel = Integer.MIN_VALUE;
        String message;
        while(true) {
            System.out.println("Enter the message:");
            message = inputUser.nextLine();
            if (message.equals("")) {
                System.out.println("Empty message!");

            }
            else {
                break;
            }
        }
        while (true) {
            System.out.println("Enter the level");
            String inputData = inputUser.nextLine();
            try {
                printLevel = Integer.parseInt(inputData);
                break;
            }
            catch (NumberFormatException c) {
                System.out.println("Invalid input!");
            }
        }

        try{
            loggerToPrint.log(printLevel,message);
        }
        catch (NullPointerException ex)
        {
            System.out.println(ex.getMessage());
        }


    }

    private void removePrinter() {
        Scanner inputUser = new Scanner(System.in);

        System.out.println("Enter 1 for removal by index, 2 for removal by type");
        int choice = inputUser.nextInt();
        if (choice == 1) {
            System.out.println("Enter index: ");
            int index = inputUser.nextInt();
            loggerToPrint.removeElement(index);
        } else {


            System.out.println("1: generic, 2: label, 3: timestamp:");
            int typeOfPrinter = 0;
            while (true) {
                String inputData = inputUser.nextLine();
                inputData = inputUser.nextLine();

                try {
                    typeOfPrinter = Integer.parseInt(inputData);
                    if (typeOfPrinter != 1 && typeOfPrinter != 2 && typeOfPrinter != 3) {
                        System.out.println("Invalid printer type, try again!");
                    } else {
                        break;
                    }
                } catch (NumberFormatException c) {
                    System.out.println("Invalid numerical value try again!");
                }
            }
            Printer printerToRemove;
            if (typeOfPrinter == 1) {
                printerToRemove = new GenericPrinter();
            } else if (typeOfPrinter == 2) {
                System.out.println("Enter the label:");
                String inputData = inputUser.nextLine();
                printerToRemove = new LabelPrinter(inputData);
            } else {
                printerToRemove = new TimeStampPrinter();
            }
            loggerToPrint.removeElement(printerToRemove);
        }
    }
    public void run(){
        Printer generic = new GenericPrinter();
        Printer timeStamp = new TimeStampPrinter();
        Printer labelPrint = new LabelPrinter("testLabel");
        generic.print("message");
        timeStamp.print("message");
        labelPrint.print("message");

        Logger paramLogger = new Logger();

        paramLogger.addPrinter(new GenericPrinter());
        paramLogger.addPrinter(new GenericPrinter());
        paramLogger.addPrinter(new LabelPrinter("asd"));
        paramLogger.addPrinter(new LabelPrinter("asda"));
        paramLogger.addPrinter(new GenericPrinter());
        paramLogger.addPrinter(new TimeStampPrinter());
        paramLogger.addPrinter(new TimeStampPrinter());

        paramLogger.removeElement(0);


        System.out.println("=================================================");
        System.out.println("=================================================");
        System.out.println("=================================================");
        System.out.println("=================================================");

        Scanner inputUser = new Scanner(System.in);
        while(true) {
            System.out.println("\n1: to set/add a printer\n2: set level\n3: log message\n4: remove printer\n0:Exit\n");
            String inputData = inputUser.nextLine();
            inputData = inputData.trim();
            if(inputData.equals("1")) {
                this.addSetPrinter();
            }
            else if(inputData.equals("2")) {
                this.setCurrentLevel();

            }
            else if(inputData.equals("3")) {
                this.logMessage();

            }
            else if(inputData.equals("4")) {
                this.removePrinter();

            }
            else if(inputData.equals("0")) {
                break;
            }
            else
            {
                System.out.println("Invalid input!");
            }

        }

    }

}
