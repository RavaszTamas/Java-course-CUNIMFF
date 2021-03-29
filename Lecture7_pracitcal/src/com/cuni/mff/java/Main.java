package com.cuni.mff.java;

import java.io.*;
import java.nio.*;
import java.nio.channels.ScatteringByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class FileCopy
{
    FileCopy(){}

    public static void CopyFileTo(String source, String target)
    {
        Path src = Paths.get(source);
        Path trg = Paths.get(target);

        System.out.println(src.toAbsolutePath());
        System.out.println(trg.toAbsolutePath());
        if(!Files.isDirectory(trg)) {
            try {
                Files.copy(src, trg, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Fail");
            }
        }
        else
        {
            Path trgD = Paths.get(target+"\\"+source);
            try {
                Files.copy(src, trgD, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Fail");
            }

        }
    }
}

public class Main {

    public static void printFileInfo(String p) {
        Path path = Path.of(p);
        if (Files.notExists(path)) {
            System.out.println("Given path does not exist");
            return;
        }
        System.out.println(path.toAbsolutePath());
        System.out.println("Directory: " + Files.isDirectory(path));
        try {
            System.out.println("Size: " + Files.size(path) + " B");
        } catch (IOException ex) {
            System.out.println("Cannot get size");
        }
    }


    public static void main(String[] args) {
        if(args.length < 2)
        {
            System.out.println("Invalid input!");
            return;
        }
        FileCopy.CopyFileTo(args[0],args[1]);

    }
}
