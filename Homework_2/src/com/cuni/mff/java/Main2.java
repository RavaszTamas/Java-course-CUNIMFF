package com.cuni.mff.java;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


class Justify2
{
    int sizeLineStandard;
    Justify2(int inSize){sizeLineStandard = inSize;}
    //Justify(String inS){originalLine = inS}

    public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }


    public String justify(String toBeJustified,int noOfWords)
    {
        if(toBeJustified.length() == sizeLineStandard)
            return toBeJustified;
        if(noOfWords==1)
            return  toBeJustified;
        int noOfSpaces = noOfWords-1;
        StringBuffer  toBeJustifiedBuffer = new StringBuffer(toBeJustified).reverse();
        toBeJustifiedBuffer.ensureCapacity(sizeLineStandard);

        int spacesToAdd = (sizeLineStandard - toBeJustifiedBuffer.length())/noOfSpaces;
        //System.out.println(spacesToAdd);
        String space = " ";
        if(spacesToAdd != 0)
            space = this.repeat(space,spacesToAdd);
        for (int i = toBeJustifiedBuffer.length() - 1; i >= 1 && toBeJustifiedBuffer.length() < sizeLineStandard; i--) {
            if (toBeJustifiedBuffer.charAt(i) != ' ' && toBeJustifiedBuffer.charAt(i-1) == ' ')
                toBeJustifiedBuffer.insert(i, space);
        }

        for (int i = toBeJustifiedBuffer.length() - 1; i >= 1 && toBeJustifiedBuffer.length() < sizeLineStandard; i--) {
            if (toBeJustifiedBuffer.charAt(i) != ' ' && toBeJustifiedBuffer.charAt(i-1) == ' ')
                toBeJustifiedBuffer.insert(i, ' ');
        }
        toBeJustifiedBuffer.insert(0,' ');
        return  toBeJustifiedBuffer.reverse().toString().trim();

    }

    public String justify(String toBeJustified)
    {
        StringBuffer  toBeJustifiedBuffer = new StringBuffer(toBeJustified).reverse();
        toBeJustifiedBuffer.ensureCapacity(sizeLineStandard);
        while(toBeJustifiedBuffer.length() < sizeLineStandard) {
            for (int i = toBeJustifiedBuffer.length() - 1; i >= 1 && toBeJustifiedBuffer.length() < sizeLineStandard; i--) {
                if (toBeJustifiedBuffer.charAt(i) != ' ' && toBeJustifiedBuffer.charAt(i-1) == ' ')
                    toBeJustifiedBuffer.insert(i, ' ');
            }
        }
        return  toBeJustifiedBuffer.reverse().toString().trim();
    }

}

public class Main2 {


    public static void main(String argv[]) {

        Writer ouputStream;
        if(argv.length == 1)
        {
            try {
                ouputStream = new FileWriter(argv[0]);
            }
            catch (IOException e)
            {
                System.out.println("Can't use/create the file!");
                return;
            }
        }
        else
        {
            ouputStream = new OutputStreamWriter(System.out);
        }
        String output ="";
        String numString = "";
        String word = "";
        int numMaxLine = 0;
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter outputPrinter = new BufferedWriter(ouputStream))
        {
            int c;
            while((c = input.read()) != -1 && (char)c != '\n')
            {
                numString += (char)c;
            }
            numMaxLine = Integer.parseInt(numString);
            Justify2 justifier = new Justify2(numMaxLine);
            Boolean isParagraph = false;
            int noOfWords = 0;
            while((c = input.read()) != -1) // read a charater
            {
                if(Character.isWhitespace((char)c)) // if it is a white spaces we have a word or paraghraph
                {
                    if(isParagraph) // if it is a case of repeated empty lines, it skips them
                        continue;
                    if(word.equals("")&& (char) c == '\n') {//we have an empty line, in the case that there is no white
                        isParagraph = true;                 // space at the end of the line, only just '\n'
                        if (!output.equals("")){            // if there is something in the output it is printed, as
                            outputPrinter.write(output.trim());     // the last paragraph line
                            outputPrinter.newLine();               // paragraph
                            outputPrinter.newLine();
                        }
                        output = "";
                        noOfWords = 0;
                        continue;
                    }
                    if(output.length() + word.length() + 1<= numMaxLine) // if the word fits in to the output it is printed
                    {
                        if(output.length() == 0)                         // empty output, word added at the start
                            output += word;
                        else
                            output += " " +  word;                       // regular output with a space appended between words
                        noOfWords++;                           // this is for the justify2 class to evaluate the rearrangement
                    }
                    else
                    {
                        if(output.equals("")) {                // if the output is empty, we have a word longer than the output space
                            output = word;                     // so it is printed out and the output is reset
                            outputPrinter.write(output.trim());
                            outputPrinter.newLine();
                            output = "";
                            noOfWords = 0;
                        }
                        else {
                            if (noOfWords == 1)
                            {       // if only one word, it is not justified
                                outputPrinter.write(output.trim());
                                outputPrinter.newLine();
                            }
                            else {
                                outputPrinter.write(justifier.justify(output, noOfWords).trim());
                                outputPrinter.newLine();
                            }// the output is justified to the size of the output
                            output = word;
                            noOfWords = 1;
                        }
                    }
                    word = "";                              //for the new word, the word string is cleaned

                }
                else { // add the next character of the word
                    isParagraph = false;
                    word += (char) c;
                }
            }
            if(!output.equals("") || !word.equals("")) {
                //print the remaining elements in the output, may not
                // be necessary; in case of no endline at the end of the input
                if(output.equals("")) // if just one word then it is printed
                {
                    outputPrinter.write(word.trim());
                    outputPrinter.newLine();
                }
                else
                {
                    if(word.equals("")) // if there is no new word the output plainly is printed
                    {
                        outputPrinter.write(output.trim());
                        outputPrinter.newLine();
                    }
                    else
                    {
                        if(output.length() + word.length() + 1<= numMaxLine) // the last paragraph line is printed
                        {
                            output += " " +  word;
                            noOfWords++;
                            outputPrinter.write(output.trim());
                            outputPrinter.newLine();
                        }
                        else
                        {
                            outputPrinter.write(justifier.justify(output, noOfWords).trim()); // if it doesn't fit the "last" line is justified and printed
                            outputPrinter.newLine();
                            outputPrinter.write(word.trim());                                 // and then the last word is also printed
                            outputPrinter.newLine();
                        }
                    }
                }
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error");
        }
        catch (IOException e)
        {

        }
    }
}