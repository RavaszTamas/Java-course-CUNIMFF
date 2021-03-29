package Java10.src.cz.cuni.mff.java.net;

import java.net.*;
import java.io.*;

public class SimpleServer {

    public static void main(String[] args) {

        try (ServerSocket s = new ServerSocket(6666)) {
            System.out.println("Server ready");
            try (Socket socket = s.accept()) {
                System.out.println("Accepting connections");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                while (true) {
                    String str = in.readLine();
                    if (str.equals("END"))
                        break;
                    out.println(str);
                }
                System.out.println("Closing connection");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
