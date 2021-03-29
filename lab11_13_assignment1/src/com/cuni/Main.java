package com.cuni;


import java.io.*;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Photo
{
    URL webLocation;
    String filename;
    Photo(URL paramWeb,String paramFile){
        webLocation = paramWeb;
        filename = paramFile;
    }
}



public class Main {

    public static void saveToFile(Photo photo)
    {
        saveToFile(photo.webLocation,photo.filename);
    }
    public static void saveToFile(URL photoLink,String filename)
    {
        try(InputStream in = photoLink.openStream()){
            Files.copy(in, Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException ex)
        {
            System.out.println("can't read photo!");
            System.out.println(ex);
        }
    }

    static ArrayList<Photo> photoStorage;

    public static void main(String[] args) {
        //args = new String[1];
        //args[0] = "https://9gag.com/";
        if(args.length != 1)
        {
            System.out.println("Invalid number of parameters!");
            return;
        }
        photoStorage = new ArrayList<>();
        try {
            URL websiteURL = new URL(args[0]);
            Pattern regex = Pattern.compile("<img src=\".*\"");

            try(BufferedReader in = new BufferedReader(new InputStreamReader(websiteURL.openStream())))
            {
                String line;
                while ((line = in.readLine()) != null)
                {
                    Matcher m = regex.matcher(line);
                    while (m.find())
                    {
                        try {
                            String PhotoLinkSring = m.group(0);
                            PhotoLinkSring = PhotoLinkSring.substring(PhotoLinkSring.indexOf("\"") + 1);
                            PhotoLinkSring = PhotoLinkSring.substring(0,PhotoLinkSring.indexOf("\""));
                            String filename = PhotoLinkSring.substring(PhotoLinkSring.lastIndexOf("/")+1);
                            System.out.println(filename + "        filename");
                            System.out.println(PhotoLinkSring);

                            if(!PhotoLinkSring.startsWith("https://"))
                            {
                                String domain = args[0].substring(args[0].indexOf("https://")+8);
                                domain = domain.substring(0,domain.indexOf("/"));
                                //System.out.println(domain);
                                PhotoLinkSring = "https://"+domain + "/"+ PhotoLinkSring;
                            }
                            System.out.println(PhotoLinkSring);
                            //System.out.println(m.group(0));
                            photoStorage.add(new Photo(new URL(PhotoLinkSring),filename));


                            //saveToFile(new URL(PhotoLinkSring),filename);

                        }
                        catch (IndexOutOfBoundsException ignored){
                        }
                    }
                }
            }
            catch (IOException ex)
            {
                System.out.println("Can't read site");
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }

        int processors = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[processors];
        for(int i = 0; i < photoStorage.size();i+=processors)
        {
            for(int j = 0; j < processors && j + i< photoStorage.size(); j++)
            {
                int val = i + j;
                threads[j] = new Thread(()->{
                    System.out.println(Thread.currentThread().getName());saveToFile(photoStorage.get(val));
                });
                threads[j].start();
            }
            for(int j = 0; j < processors && j < photoStorage.size(); j++)
            {
                try {
                    threads[j].join();
                } catch (InterruptedException e) {

                }
            }

        }

    }

}
