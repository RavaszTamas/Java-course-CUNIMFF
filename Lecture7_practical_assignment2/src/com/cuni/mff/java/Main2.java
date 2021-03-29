package com.cuni.mff.java;

import java.io.*;
import java.nio.file.Files;

public class Main2 {

    public static void main(String[] args) {
        while (true)
        {
            try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in)))
            {
                String line = input.readLine();
                System.out.println(line);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }

}
