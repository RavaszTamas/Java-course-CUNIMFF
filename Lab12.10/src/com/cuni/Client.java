package com.cuni;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;

public class Client {

    static void printHints()
    {
        System.out.println("Invalid number of input parameters!");
        System.out.println("Use: -command{-a,-l,-r,-d}");
        System.out.println(" -a priority message");

    }

    static HashSet<String> commands = new HashSet<>();

    static boolean validCommand(String[] params)
    {
        if(params[0].equals("-a"))
        {
            if(params.length != 3)
                return false;
            try{
                Integer.parseInt(params[1]);
            }
            catch (NumberFormatException e)
            {
                return  false;
            }
        }
        else if(params[0].equals("-r")){
            if(params.length != 1)
                return false;

        }
        else if(params[0].equals("-l")){
            if(params.length != 1)
                return false;

        }
        else if(params[0].equals("-d")){
            if(params.length != 1)
                return false;

        }
        return true;
    }

    static void printIncreasing(String[] args)
    {
        try {
            InetAddress addr = InetAddress.getByName(null);
            Socket socket = new Socket(addr, 6666);
            try(DataInputStream input = new DataInputStream(socket.getInputStream()); DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
            {
                String command = String.join(" ",args);
                output.writeUTF(command);
                output.flush();
                System.out.println("Sending finished!");
                String result;
                try {
                    while ((result = (String) input.readUTF()) != null)
                        System.out.println(result);
                }
                catch (EOFException e)
                {

                }
                System.out.println("Reading finished!");

            }
            catch (IOException e)
            {
                System.out.println("Error with communication stream");
                System.out.println("Terminating...");
            }

        } catch (UnknownHostException e) {
            System.out.println("Error with communication Host");
            System.out.println("Terminating...");
        } catch (IOException e) {
            System.out.println("Error with communication IO");
            System.out.println("Terminating...");

        }
    }

    static void printDecresaing(String[] args)
    {

        try {
            InetAddress addr = InetAddress.getByName(null);
            Socket socket = new Socket(addr, 6666);
            try(DataInputStream input = new DataInputStream(socket.getInputStream()); DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
            {
                String command = String.join(" ",args);
                output.writeUTF(command);
                output.flush();
                //System.out.println("Sending finished!");
                String result;
                try {
                    while ((result = (String) input.readUTF()) != null)
                        System.out.println(result);
                }
                catch (EOFException e)
                {

                }
                //System.out.println("Reading finished!");

            }
            catch (IOException e)
            {
                System.out.println("Error with communication stream");
                System.out.println("Terminating...");
            }

        } catch (UnknownHostException e) {
            System.out.println("Error with communication Host");
            System.out.println("Terminating...");
        } catch (IOException e) {
            System.out.println("Error with communication IO");
            System.out.println("Terminating...");

        }





    }

    static void addElement(String[] args)
    {

        try {
            InetAddress addr = InetAddress.getByName(null);
            Socket socket = new Socket(addr, 6666);
            try(DataInputStream input = new DataInputStream(socket.getInputStream()); DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
            {
                String command = String.join(" ",args);
                output.writeUTF(command);
                output.flush();
                //System.out.println("Sending finished!");
                String result;
                try {
                    while ((result = (String) input.readUTF()) != null)
                        System.out.println(result);
                }
                catch (EOFException e)
                {

                }
                //System.out.println("Reading finished!");

            }
            catch (IOException e)
            {
                System.out.println("Error with communication stream");
                System.out.println("Terminating...");
            }

        } catch (UnknownHostException e) {
            System.out.println("Error with communication Host");
            System.out.println("Terminating...");
        } catch (IOException e) {
            System.out.println("Error with communication IO");
            System.out.println("Terminating...");

        }

    }

    public static void main(String[] args) {
        if(args.length < 1 || args.length > 3) {
            printHints();
            return;
        }
        commands.add("-a");
        commands.add("-l");
        commands.add("-r");
        commands.add("-d");
        if(commands.contains(args[0]) && validCommand(args))
        {
            if(args[0].equals("-a"))
            {
                addElement(args);
            }
            else if(args[0].equals("-l"))
            {
                printDecresaing(args);
            }
            else if(args[0].equals("-r"))
            {
                printDecresaing(args);

            }

        }
        else {
            printHints();
            System.out.println("Terminating...");
        }
        /*
        try {
            switch (args[0]) {
                case "-a": {
                    try {
                        addToList(Integer.parseInt(args[1]), args[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid value, use integer for priority!");
                    }
                    break;
                }
                case "-l": {
                    if (args.length != 1)
                    {
                        printHints();
                        break;
                    }
                    printAllDecreasing();
                    break;
                }
                case "-r": {
                    if (args.length != 1)
                    {
                        printHints();
                        break;
                    }
                    printAllIncreasing();
                    break;
                }
                case "-d": {
                    if (args.length != 1)
                    {
                        printHints();
                        break;
                    }
                    deleteFromList();
                    break;
                }
                default: {
                    printHints();
                    break;

                }
            }
        }
        catch (RepoException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Terminating...");
        }
        */

    }
}
