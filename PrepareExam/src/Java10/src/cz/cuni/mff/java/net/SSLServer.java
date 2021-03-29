package Java10.src.cz.cuni.mff.java.net;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
Requires a certificate.
It can be generated with:
keytool -genkey -keystore nameOfFile -keyalg RSA

Then needs to be executed as follows:
java -cp . -Djavax.net.ssl.keyStore=nameOfFile -Djavax.net.ssl.keyStorePassword=password cz.cuni.mff.java.net.SSLServer
 */

public class SSLServer {
    public static void main(String[] args) {

        ServerSocketFactory factory = SSLServerSocketFactory.getDefault();

        try (ServerSocket s = factory.createServerSocket(6666)) {
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
