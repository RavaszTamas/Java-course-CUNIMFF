package Java10.src.cz.cuni.mff.java.net;

import java.net.*;
import java.io.*;

public class GetURL {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.mff.cuni.cz/");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    break;
                }
                System.out.println(str);
            }
        }
    }
}
