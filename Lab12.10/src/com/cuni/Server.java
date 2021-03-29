package com.cuni;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

class RequestEvaluator extends Thread
{
    private Socket mySocket;
    private FileRepository repo;
    ReadWriteLock readWriteLock;
    RequestEvaluator(Socket s, FileRepository paramRepo, ReadWriteLock paramreadWriteLock) throws IOException
    {
        mySocket = s;
        repo = paramRepo;
        readWriteLock = paramreadWriteLock;
    }

    @Override
    public void run()
    {
        try(DataInputStream input = new DataInputStream(mySocket.getInputStream()); DataOutputStream out = new DataOutputStream(mySocket.getOutputStream())) {
            String request = (String)input.readUTF();
            String[] params = request.split(" ");
            if(params.length < 1 || params.length > 3) {
                out.writeUTF("-1");
            }
            else {
                try {
                    switch (params[0]) {
                        case "-a": {
                            try {
                                addToList(Integer.parseInt(params[1]), params[2]);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid value, use integer for priority!");
                            }
                            break;
                        }
                        case "-l": {
                            if (params.length != 1) {
                                out.writeUTF("-1");
                                break;
                            }
                            printAllDecreasing(out);
                            break;
                        }
                        case "-r": {
                            if (params.length != 1) {
                                out.writeUTF("-1");
                                break;
                            }
                            printAllIncreasing(out);
                            break;
                        }
                        case "-d": {
                            if (params.length != 1) {
                                out.writeUTF("-1");
                                break;
                            }
                            deleteFromList();
                            break;
                        }
                        default: {
                            out.writeUTF("-1");
                            break;

                        }
                    }
                } catch (RepoException e) {

                }
            }
        } catch (IOException e) {

        }

    }

    public synchronized void printAllDecreasing(DataOutputStream out) throws RepoException, IOException {

        readWriteLock.readLock().lock();
        List<Message> sortedList = null;
        try {
            sortedList = repo.readALl().stream().sorted(Comparator.comparingInt(Message::getPriority)).collect(Collectors.toList());
        }
        finally {
            readWriteLock.readLock().unlock();
        }
        for (Message item : sortedList) {
            out.writeUTF(item.toString());
        }

    }



    public synchronized void printAllIncreasing(DataOutputStream out) throws RepoException, IOException {
        readWriteLock.readLock().lock();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Message> sortedList;
        try {

            sortedList = repo.readALl().stream().sorted(Comparator.comparingInt(Message::getPriority).reversed()).collect(Collectors.toList());
        }
        finally {
            readWriteLock.readLock().unlock();
        }

        for (Message item : sortedList) {
            out.writeUTF(item.toString());
        }

    }

    public synchronized void addToList(int priority, String message) throws RepoException {
        readWriteLock.writeLock().lock();
        try {
            repo.add(new Message(priority, message));
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public synchronized void deleteFromList() throws RepoException
    {
        List<Message> sortedList = repo.readALl().stream().sorted(Comparator.comparingInt(Message::getPriority)).collect(Collectors.toList());
        int i = 0;
        System.out.println("ID, Priority, Message");
        for (Message msg: sortedList)
        {
            System.out.println(i++ + ": " + msg.getPriority() + " " + msg.getMessage());
        }
        System.out.println("Enter the id of the message you want to delete");
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in))){
            int id = Integer.parseInt(input.readLine());
            sortedList.remove(id);
            repo.writeAll(sortedList);
        }
        catch (IOException e)
        {
            System.out.println("Problem with input!");
        }
        catch (NumberFormatException | IndexOutOfBoundsException e)
        {
            System.out.println("Invalid id!");
        }
    }

}


public class Server {

    static ReadWriteLock  readWriteLock = new ReentrantReadWriteLock();
    private static Path fileLocation;
    private static FileRepository repo;



    public static void main(String[] args) {

        fileLocation = Paths.get("TODO.txt");
        repo = new FileRepository(fileLocation);
        try
        {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Server running!");
            while (true)
            {
                try(Socket socket = serverSocket.accept()) {
                    new RequestEvaluator(socket, repo, readWriteLock).start();
                }
            }
        }
        catch (IOException e)
        {

        }
    }

}
