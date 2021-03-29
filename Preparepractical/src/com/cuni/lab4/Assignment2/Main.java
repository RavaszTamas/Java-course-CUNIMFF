package com.cuni.lab4.Assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    static boolean canRead = true;
    public static String readWord(BufferedReader input) throws IOException
    {
        canRead = true;
        StringBuilder str = new StringBuilder();
        int c;
        while ((c = input.read())!=-1 && !Character.isWhitespace(c))
        {
            str.append((char)c);
        }
        if(c == -1)
            canRead = false;
        return str.toString();
    }
    public static void main(String[] args) {
        if(args.length != 1)
        {
            System.out.println("Only one file!");
            return;
        }
        Path p = Paths.get(args[0]);
        if(!Files.exists(p.toAbsolutePath()))
        {
            System.out.println("Invalid file!");
            return;
        }
        try(BufferedReader input = new BufferedReader(new FileReader(args[0]))){
            while(canRead)
            {
                String word = readWord(input).trim();
                if(word.length() != 0)
                    System.out.println(word);
            }

        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
}
