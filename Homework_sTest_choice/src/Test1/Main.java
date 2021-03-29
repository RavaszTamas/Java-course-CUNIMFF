package Test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

interface Question
{

}

class singleChoice implements Question
{

}
class multiChoice implements Question
{

}

class InvalidQuestion extends Exception
{
    public InvalidQuestion(String message)
    {
        super(message);
    }
}


public class Main {

    HashMap<Integer,Question> questionHashMap = new HashMap<>();

    static void ReadQuestion(BufferedReader input) throws InvalidQuestion
    {

    }

    public static void main(String[] args) {

        try(BufferedReader input = new BufferedReader(new FileReader("INPUT")))
        {
                try
                {
                    ReadQuestion(input);

                }
                catch (InvalidQuestion e)
                {
                    System.out.println(e.getMessage());
                }
        }
        catch (IOException e)
        {

        }


    }
}
