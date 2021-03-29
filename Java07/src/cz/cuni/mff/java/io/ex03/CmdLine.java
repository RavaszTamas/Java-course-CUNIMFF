package cz.cuni.mff.java.io.ex03;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdLine {

    public static String getLine(String prefix) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print(prefix);
            return br.readLine();
        }
    }

    public static String getLineViaConsole(String prefix) throws IOException {
        Console c = System.console();
        if (c == null) {
            System.out.println("Console is not available");
            throw new IOException();
        }
        c.printf(prefix);
        return c.readLine();
    }

    public static void main(String[] args) {
        try {
            //System.out.println(getLine("> "));
            System.out.println(getLineViaConsole("> "));
        } catch (IOException ex) {
            System.out.println("IOException occurred");
        }
    }
}
