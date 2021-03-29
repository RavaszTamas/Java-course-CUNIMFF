package com.cuni.mff.java;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Justify
{
    int sizeLineStandard;
    Justify(int inSize){sizeLineStandard = inSize;}
    //Justify(String inS){originalLine = inS}

    public String justify(ArrayList<String> toBeJustified)
    {
        int lineSize = 0;
        if(toBeJustified.size() == 1)
            return toBeJustified.get(0);
        for(String s: toBeJustified)
        {
            lineSize += s.length();
        }
        while(lineSize < sizeLineStandard) {
            int i = 1;
            while (i < toBeJustified.size() && lineSize < sizeLineStandard) {
                if (!toBeJustified.get(i).equals(" ")){
                    toBeJustified.add(i - 1, " ");
                    lineSize += 1;
                    i+=1;
                }
                i+=1;
            }
        }
        String outString="";
        for(String s: toBeJustified)
        {
            outString += s;
        }
        return  outString;
    }

}

public class Main {

    public static void main(String argv[]) {

        int numMaxLine = 0;
        Queue<String> wordQueue = new LinkedList<>();
        ArrayList<String> stringsToJustify = new ArrayList<>();

        try(Scanner sc2 = new Scanner(System.in)) {
            if(sc2.hasNextLine())
                numMaxLine = Integer.parseInt(sc2.nextLine()); // read in the number
            if(numMaxLine <= 0)
                throw new NumberFormatException();

            Justify justifier = new Justify(numMaxLine);
            Boolean notEnded = true;
            Boolean endParagraph = false;
            String line = null;

            do {
                line = sc2.nextLine().trim(); // read in the first line
            }

            while(sc2.hasNextLine() && line.length() == 0);  // read in the first line

            while (sc2.hasNextLine() && notEnded) {
                String nextLine = sc2.nextLine().trim(); // read the second line
                endParagraph = false;
                if(nextLine.length() == 0) // if the next line is empty then it is end of paragraph
                {
                    endParagraph = true;
                    while (sc2.hasNextLine() && nextLine.length() == 0){nextLine = sc2.nextLine().trim();} // checking for more empty lines
                    if(!sc2.hasNextLine())// if no next line, it has ended
                        notEnded = false;
                }
                if(endParagraph)//if it is the end of a paragraph is found
                {
                    int currentLineLength = 0;

                    InputStream lineStream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8)); // adding it to a stream to read it easily
                    Scanner wordScanner = new Scanner(lineStream);

                    while (wordScanner.hasNext())
                        wordQueue.add(wordScanner.next()); //adding the new words to the queue of words

                    while(!wordQueue.isEmpty()) { // it goes up until we printed all words
                        currentLineLength = 0;
                        stringsToJustify.add(wordQueue.remove());//first word is added implicitly
                        currentLineLength += stringsToJustify.get(0).length();//first word is added implicitly
                        while (!wordQueue.isEmpty() && currentLineLength + 1 + wordQueue.peek().length() <= numMaxLine)//if nothing more can fit end it
                        {
                            stringsToJustify.add(" ");
                            stringsToJustify.add(wordQueue.remove()); //adding the words to the list of strings to be justified
                            currentLineLength += stringsToJustify.get(stringsToJustify.size()-1).length()+1; // increasing the line length currently
                        }
                        if(wordQueue.isEmpty())
                        {
                            String outstring ="";
                            for(String s: stringsToJustify)
                                outstring += s;
                            System.out.println(outstring);
                            if(notEnded)
                                System.out.println("");
                            //System.out.println("end");
                        }
                        else if(stringsToJustify.size() != 0) {
                            System.out.println(justifier.justify(stringsToJustify));
                            //System.out.println("notend");
                        }
                        stringsToJustify.clear();
                    }
                }
                else if(!(notEnded == false && line.length() == 0)) {
                    InputStream lineStream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8));
                    Scanner wordScanner = new Scanner(lineStream);
                    int currentLineLength = 0;
                    while (wordScanner.hasNext())
                        wordQueue.add(wordScanner.next());
                    Boolean go = true;
                    while(!wordQueue.isEmpty() && go) {
                        currentLineLength = 0;
                        stringsToJustify.add(wordQueue.remove());
                        currentLineLength += stringsToJustify.get(0).length();
                        while (!wordQueue.isEmpty() && currentLineLength + 1 + wordQueue.peek().length() <= numMaxLine) {
                            stringsToJustify.add(" ");
                            stringsToJustify.add(wordQueue.remove());
                            currentLineLength += stringsToJustify.get(stringsToJustify.size() - 1).length() + 1;
                        }
                        if (wordQueue.isEmpty()) {
                            stringsToJustify.removeAll(Collections.singleton(" "));
                            for (String s : stringsToJustify)
                                wordQueue.add(s);
                            go = false;
                        } else if (stringsToJustify.size() != 0) {
                            System.out.println(justifier.justify(stringsToJustify));
                        }
                        stringsToJustify.clear();
                    }

                    stringsToJustify.clear();

                }

                line = nextLine;
            }
            if(!wordQueue.isEmpty()) {
                int currentLineLength = 0;

                InputStream lineStream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8));
                Scanner wordScanner = new Scanner(lineStream);

                while (wordScanner.hasNext())
                    wordQueue.add(wordScanner.next());

                while (!wordQueue.isEmpty()) {
                    currentLineLength = 0;
                    stringsToJustify.add(wordQueue.remove());
                    currentLineLength += stringsToJustify.get(0).length();
                    while (!wordQueue.isEmpty() && currentLineLength + 1 + wordQueue.peek().length() <= numMaxLine) {
                        stringsToJustify.add(" ");
                        stringsToJustify.add(wordQueue.remove());
                        currentLineLength += stringsToJustify.get(stringsToJustify.size() - 1).length() + 1;
                    }
                    if (wordQueue.isEmpty()) {
                        String outstring = "";
                        for (String s : stringsToJustify)
                            outstring += s;
                        System.out.println(outstring);
                        //System.out.println("end");
                    } else if (stringsToJustify.size() != 0) {
                        System.out.println(justifier.justify(stringsToJustify));
                        //System.out.println("notend");
                    }
                    stringsToJustify.clear();
                }
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /*

        try(BufferedReader input= new BufferedReader( new InputStreamReader(System.in)))
        {
            int c;
            String word ="";
            int num;
            String initLine;
            initLine = input.readLine();
            num = Integer.parseInt(initLine);
            String outputLine = "";
            String inputLine = "";
            while((inputLine = input.readLine())!=null)
            {
                String[] words = inputLine.split(" ");
                while((words.length == 1 && words[0].equals(""))||words.length == 0)

                System.out.println("\""+inputLine+"\"");
                System.out.println(inputLine.isEmpty());
                for(Object a: words)
                    System.out.println("\""+a+"\"");
                System.out.println(words.length);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error");
        }
        catch(NumberFormatException e)
        {
            System.out.println("Error");
        }
        */
    }

}
