package cz.cuni.mff.java.io.ex02;

import java.io.*;
import java.nio.file.Path;

public class Cat {
    public static void printTextFile(String p) {
        Path path = Path.of(p);
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
        // or better way to open
        // try (BufferedReader br = Files.newBufferedReader(path)) {
            int c;
            while ((c = br.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            System.out.println("IOException occurred");
        }
    }

    public static void printBinFile(String p) {
        Path path = Path.of(p);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            // or better way to open
            // try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path))) {
            int c;
            while ((c = bis.read()) != -1) {
                System.out.print(Integer.toHexString(c));
                System.out.print(" ");
            }
        } catch (IOException ex) {
            System.out.println("IOException occurred");
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No path");
            System.exit(1);
        }
        printTextFile(args[0]);
        printBinFile(args[0]);
    }
}
