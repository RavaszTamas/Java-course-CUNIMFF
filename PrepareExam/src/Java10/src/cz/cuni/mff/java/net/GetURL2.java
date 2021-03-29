package Java10.src.cz.cuni.mff.java.net;

import java.net.*;
import java.io.*;

public class GetURL2 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.mff.cuni.cz/");
            URLConnection con = url.openConnection();
            con.connect();
            System.out.println("Content-Type: " + con.getContentType());
            System.out.println("Length: " + con.getContentLength());
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                while (true) {
                    String str = in.readLine();
                    if (str == null) {
                        break;
                    }
                    System.out.println(str);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
