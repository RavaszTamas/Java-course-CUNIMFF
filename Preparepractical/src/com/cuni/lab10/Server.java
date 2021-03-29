package com.cuni.lab10;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class RequestEvaluaor extends Thread
{

    private Socket socket;

    public RequestEvaluaor(Socket s)
    {
        socket = s;
    }
    @Override
    public void run() {
        try(DataOutputStream out = new DataOutputStream(socket.getOutputStream()))
        {
            writeAllIncreasing(out);
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    private void writeAllIncreasing(DataOutputStream out) {

        try {
            List<Message> data = readAll();
            data = data.stream().sorted(Comparator.comparingInt(Message::getPriority)).collect(Collectors.toList());
            data.forEach(System.out::println);
        }
        catch (repoException ex)
        {
            System.out.println(ex);
        }
    }

    String filename = "TODO.txt";

    List<Message> readAll() throws repoException {
        List<String> lines;
        List<Message> messages = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename));
            for(String line : lines)
            {
                var params = line.split(";");
                messages.add(new Message(Integer.parseInt(params[0]),params[1]));
            }
        } catch (IOException e) {
            throw new repoException("Can't read file");
        }
        return messages;
    }
}

public class Server {

    public static void main(String[] args) {


        try {
            ServerSocket ssocket = new ServerSocket(6666);
            while (true)
            {
                try(Socket socket = ssocket.accept())
                {
                    new RequestEvaluaor(socket).start();
                }
                catch (IOException e)
                {
                    System.out.println(e);
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
