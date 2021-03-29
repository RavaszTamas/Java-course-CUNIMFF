package Assignment_1;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void printAllDecreasing() throws RepoException {

        List<Message> sortedList = repo.readALl().stream().sorted(Comparator.comparingInt(Message::getPriority)).collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }

    public static void printAllIncreasing() throws RepoException {
        List<Message> sortedList = repo.readALl().stream().sorted(Comparator.comparingInt(Message::getPriority).reversed()).collect(Collectors.toList());
        sortedList.forEach(System.out::println);

    }

    public static void addToList(int priority, String message) throws RepoException {
        repo.add(new Message(priority,message));
    }

    public static void deleteFromList() throws RepoException
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

    public static void printHints()
    {
        System.out.println("Invalid number of input parameters!");
        System.out.println("Use: -command{-a,-l,-r,-d}");
        System.out.println(" -a priority message");

    }

    private static Path fileLocation = Paths.get("TODO.txt");
    static FileRepository repo;
    public static void main(String[] args) {
        if(args.length < 1 || args.length > 3) {
            printHints();
            return;
        }

         repo = new FileRepository(fileLocation);
        try {
            switch (args[0]) {
                case "-a": {
                    try {
                        addToList(Integer.parseInt(args[1]), args[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid value, use integer for priority!");
                    }
                    break;
                }
                case "-l": {
                    if (args.length != 1)
                    {
                        printHints();
                        break;
                    }
                    printAllDecreasing();
                    break;
                }
                case "-r": {
                    if (args.length != 1)
                    {
                        printHints();
                        break;
                    }
                    printAllIncreasing();
                    break;
                }
                case "-d": {
                    if (args.length != 1)
                    {
                        printHints();
                        break;
                    }
                    deleteFromList();
                    break;
                }
                default: {
                    printHints();
                    break;

                }
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            printHints();
            System.out.println("Terminating...");
        }
        catch (RepoException e)
        {
            System.out.println(e.getMessage());
            printHints();
            System.out.println("Terminating...");
        }

    }
}
