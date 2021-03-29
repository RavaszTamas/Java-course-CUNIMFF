package com.cuni.lab10;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {



    public static void main(String[] args) {
        printDecreasing();
    }

    private static void printDecreasing() {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            Socket socket = new Socket(addr,6666);
            try(DataInputStream in = new DataInputStream(socket.getInputStream()))
            {
                String line;
                while ((line = in.readUTF()) != null)
                    System.out.println(line);
            }
            catch (EOFException ex)
            {

            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }


}
