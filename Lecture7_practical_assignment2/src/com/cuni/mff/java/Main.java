package com.cuni.mff.java;

import com.cuni.mff.java.Domain.User;
import com.cuni.mff.java.MyExceptions.ExistingUserID;
import com.cuni.mff.java.MyExceptions.ExistingUserName;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

class addUser
{
    Path repositoryPath;
    ArrayList<User> userRepository;
    public addUser(Path mainDirectory){
        repositoryPath = mainDirectory;
        userRepository = new ArrayList<>();
        readAllData();
    }

    public void readAllData()
    {
        try(BufferedReader input = Files.newBufferedReader(repositoryPath))
        {
            String line;
            while((line = input.readLine()) != null)
            {
                String[] params = line.split(":");
                userRepository.add(new User(params[0],params[1],params[2],params[3],params[4],params[5],params[6]));
            }

        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void writeAllData()
    {
        try(BufferedWriter output = Files.newBufferedWriter(repositoryPath))
        {
            for(User u : userRepository)
            {
                output.write(String.join(":",u.getName(),u.getPassword(),u.getUID(),u.getGID(),u.getGECOS(),u.getDirectory(),u.getShell()));
                output.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.out.println("Exiting system");
            System.exit(0);
        }

    }

    public void checkUserName(String name) throws ExistingUserName
    {
        if(getAllUsername().contains(name))
            throw  new ExistingUserName();

    }

    ArrayList<String> getAllUsername()
    {
        ArrayList<String> UserIds = new ArrayList<>();
        for(User u : userRepository)
        {
            UserIds.add(u.getName());
        }
        return UserIds;

    }
    public ArrayList<String> getAllUserID()
    {
        ArrayList<String> UserIds = new ArrayList<>();
        for(User u : userRepository)
        {
            UserIds.add(u.getUID());
        }
        return UserIds;
    }

    public ArrayList<String> getAllGroupId()
    {
        ArrayList<String> GroupIds = new ArrayList<>();
        for(User u : userRepository)
        {
            if(!GroupIds.contains(u.getGID()))
                GroupIds.add(u.getGID());
        }
        return GroupIds;
    }


    public boolean UserIDExists(String uidToCheck)
    {
        return  getAllUserID().contains(uidToCheck);
    }

    public  void UserIDExistsThrow(String uidToCheck) throws ExistingUserID {
        if(UserIDExists(uidToCheck))
            throw  new ExistingUserID();
    }

    public String suggestUID()
    {
        Random rand = new Random(System.currentTimeMillis());
        var allUserIDS = getAllUserID();
        boolean notFound = true;
        String IDString = null;
        while (notFound) {
            int newID = rand.nextInt(1000000);
            IDString = Integer.toString(newID);
            if(!allUserIDS.contains(IDString))
                notFound = false;
        }
        return IDString;
    }

    public String getRandomGUID()
    {
        Random rnd = new Random(System.currentTimeMillis());

        ArrayList<String> GUIDS = getAllGroupId();
        return GUIDS.get(rnd.nextInt(GUIDS.size()));

    }

    public void AddElment(String paramUserName,String paramUID, String paramHomeDirectory,String paramBash)
    {
        User newUser = new User(paramUserName,"x",paramUID,getRandomGUID(),"Some Name",paramHomeDirectory,paramBash);
        userRepository.add(newUser);
        writeAllData();
    }

    public void run()
    {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in)))
        {
            while (true) {
                boolean invalidUserName = true;
                String paramUserName = "";
                while (invalidUserName) {

                    System.out.println("Enter a user name:");
                    paramUserName = input.readLine();
                    paramUserName = paramUserName.trim();
                    try {
                        checkUserName(paramUserName);
                        invalidUserName = false;
                    } catch (ExistingUserName e) {
                        System.out.println("Username already exists!");
                    }
                }
                String paramUID = "";
                boolean acceptedUID = false;
                while (!acceptedUID) {
                    paramUID = suggestUID();
                    System.out.println("Suggested UID: " + paramUID);
                    System.out.println("Do you accept it?");
                    String  answer = null;
                    while (!answer.equals('Y') && !answer.equals('N')) {
                        System.out.println("[Y/N]");
                        answer = input.readLine();
                    }
                    if (answer.equals('N')) {
                        answer = "";
                        System.out.println("Want to enter a specific ID?");
                        while (!answer.equals('Y') && !answer.equals('N')) {
                            System.out.println("[Y/N]");
                            answer = input.readLine();
                        }
                        if (answer.equals('Y')) {
                            boolean enteredValid = false;
                            int newID = -1;
                            while (!enteredValid) {
                                System.out.println("Enter ID:");
                                try {
                                    newID = Integer.parseInt(input.readLine());
                                    UserIDExistsThrow(Integer.toString(newID));
                                    paramUID = Integer.toString(newID);
                                    enteredValid = true;
                                } catch (ExistingUserID e) {
                                    System.out.println("User ID already exists");
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid id try again");
                                }
                            }
                            acceptedUID = true;
                        }
                    } else
                        acceptedUID = true;

                }
                String paramHomeDirectory = "home/" + paramUserName;
                boolean invalidHomeDirectory = true;

                while(invalidHomeDirectory) {
                    String  answer = "";
                    System.out.println("Want to enter a specific home directory?");
                    while (!answer.equals('Y') && !answer.equals('N')) {
                        System.out.println("[Y/N]");
                        answer = input.readLine();
                    }

                    if (answer.equals('Y')) {
                        boolean enteredValid = false;
                        while (!enteredValid)
                        {
                            try
                            {
                                System.out.println("Enter the directory");
                                String newHomeDirectory = input.readLine();
                                File file = new File(newHomeDirectory);

                                if(file.exists() && file.isDirectory())
                                {
                                    enteredValid = true;
                                    invalidHomeDirectory = false;
                                    paramHomeDirectory = newHomeDirectory;
                                }
                                else {
                                    System.out.println("Invalid directory");
                                }
                            }
                            catch (IOException e)
                            {
                                System.out.println("Exception with IO");
                                System.exit(0);
                            }
                        }
                    }
                    else
                    {
                        invalidHomeDirectory = false;
                    }
                }
                String paramBash = "/bin/bash";
                boolean invalidBash = true;
                while(invalidBash) {
                    String  answer = "";
                    System.out.println("Want to enter a specific shell?");
                    while (!answer.equals('Y') && !answer.equals('N')) {
                        System.out.println("[Y/N]");
                        answer = input.readLine();
                    }

                    if (answer.equals('Y')) {
                        boolean enteredValid = false;
                        while (!enteredValid)
                        {
                            try
                            {
                                System.out.println("Enter the shell");
                                String newShell = input.readLine();
                                if(!newShell.equals(""))
                                    enteredValid = true;
                            }
                            catch (IOException e)
                            {
                                System.out.println("Exception with IO");
                                System.exit(0);
                            }
                        }
                        invalidBash =false;
                    }
                    else
                    {
                        invalidBash = false;
                    }
                }

                AddElment(paramUserName,paramUID,paramHomeDirectory,paramBash);
                System.out.println(paramUserName + "  " + paramHomeDirectory + "  " + paramUID + " " + paramBash);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }


}

public class Main {

    public static void main(String[] args) {
        addUser newUI;
        if(args.length == 0)
            newUI = new addUser(Paths.get("passwd.txt"));
        else
        {
            var newDirectory = Paths.get(args[0]);
            if(!Files.exists(newDirectory))
            {
                try
                {
                    Files.createFile(newDirectory);
                }
                catch (IOException e)
                {
                    System.out.println("Can't creat file, exiting 1");
                    System.exit(1);
                }
            }
            newUI = new addUser(newDirectory);
        }
        newUI.run();
    }
}
