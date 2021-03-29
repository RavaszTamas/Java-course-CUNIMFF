package homework2;

import java.io.BufferedReader;
import java.io.*;

public class main {
	
	public static void main(String argv[]) {
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
;			while((inputLine = input.readLine())!=null)
			{
				String[] words = inputLine.split(" ");
				//while((words.length == 1 && words[0].equals(""))||words.length == 0)
				
				System.out.println("\""+inputLine+"\"");
				System.out.println(inputLine.isEmpty());
				for(Object a: words)
					System.out.println("\""+a+"\"");
				System.out.println(words.length);
			}
		}
		catch(IOException e)
		{
			
		}
		catch(NumberFormatException e)
		{
			System.out.println("Error");
		}
		
	}

}
