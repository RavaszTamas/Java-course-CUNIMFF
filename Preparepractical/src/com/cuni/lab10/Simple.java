package com.cuni.lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class repoException extends Exception
{
    repoException(String msg)
    {
        super(msg);
    }
}

class Message{
    private int priority;
    private String message;

    Message(int prio,String msg)
    {
        priority = prio;
        message = msg;
    }

    public int getPriority() {
        return priority;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "priority=" + priority + ", message='" + message+"\'";
    }
}

public class Simple {

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

    void readAllIncreasing()
    {
        try {
            List<Message> msgs = readAll().stream().sorted(Comparator.comparingInt(Message::getPriority)).collect(Collectors.toList());
            msgs.forEach(System.out::println);
        } catch (repoException e) {
            e.printStackTrace();
        }
    }

    void readAllDecreasing()
    {
        try {
            List<Message> msgs = readAll().stream().sorted(Comparator.comparingInt(Message::getPriority).reversed()).collect(Collectors.toList());
            msgs.forEach(System.out::println);
        } catch (repoException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Simple s = new Simple();
        s.readAllIncreasing();
        s.readAllDecreasing();
    }

}
