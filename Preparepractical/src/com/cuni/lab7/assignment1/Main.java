package com.cuni.lab7.assignment1;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void useChannel(String inFileString, String outFileString)
    {
        try(FileChannel in = new FileInputStream(inFileString).getChannel(); FileChannel out = new FileOutputStream(outFileString).getChannel())
        {
            in.transferTo(0,in.size()-1,out);
            //out.transferFrom(in,0,in.size()-1);
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    public static void bruteForce(String inFileString, String outFileString)
    {
        try(BufferedReader in = new BufferedReader(new FileReader(inFileString));BufferedWriter out = new BufferedWriter(new FileWriter(outFileString)))
        {
            int b;
            while ((b = in.read()) != -1)
            {
                out.write(b);
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }

    }

    public static void fileCopy(String inFileString, String outFileString)
    {
        try {
            Files.copy(Paths.get(inFileString),Paths.get(outFileString), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        //args = new String[2];
        //args[0] = "asd.txt";
        //args[1] = "empty/";
        if(args.length != 2)
        {
            System.out.println("Invalid number of parameters!");
            return;
        }
        Path firstFile = Paths.get(args[0]);
        Path secondFile = Paths.get(args[1]);
        if(!Files.exists(firstFile) || !Files.exists(secondFile))
        {
            System.out.println("The file(s) doesn't exist!");
            return;
        }
        if(!Files.isRegularFile(firstFile))
        {
            System.out.println("Not a file!");
            return;
        }
        if(!Files.isReadable(firstFile))
        {
            System.out.println("Not readable!");
            return;
        }

        if(Files.isDirectory(secondFile))
        {
            args[1] = args[1] + "/" + args[0];

        }
        //useChannel(args[0],args[1]);
        //fileCopy(args[0],args[1]);
        bruteForce(args[0],args[1]);
    }
}
