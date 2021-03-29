package com.cuni;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class RepoException extends Exception
{
    RepoException(String message)
    {
        super(message);
    }
}

public class FileRepository {
    private Path filePath;
    public FileRepository(Path paramPath){filePath = paramPath;}

    void add(Message newMessage) throws RepoException {
        try(BufferedWriter writerAppend = new BufferedWriter(new FileWriter(filePath.toFile(),true)))
        {
            writerAppend.write(newMessage.getPriority() + " " + newMessage.getMessage());
            writerAppend.newLine();
        }
        catch (IOException e)
        {
            throw new RepoException("Problem with file writing!");
        }
    }

    void writeAll(List<Message> messages)throws RepoException
    {
        try(PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(filePath.toFile()))))
        {
            messages.forEach(o -> output.println(o.getPriority() + " " + o.getMessage()));
        }
        catch (IOException e)
        {
            throw new RepoException("Problem with writing to file!");
        }
    }

    List<Message> readALl() throws RepoException
    {
        List<Message> elements = new ArrayList<>();
        String line;
        try(BufferedReader input = new BufferedReader(new FileReader(filePath.toFile())))
        {
            while ((line = input.readLine()) != null)
            {
                var params = line.split(" ");
                elements.add(new Message(Integer.parseInt(params[0]),params[1]));
            }
        }
        catch (IOException e)
        {
            throw new RepoException("Problem with reading the file!");
        }
        return  elements;
    }
}
