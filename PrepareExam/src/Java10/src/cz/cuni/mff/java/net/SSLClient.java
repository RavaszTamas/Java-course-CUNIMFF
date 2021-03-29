package Java10.src.cz.cuni.mff.java.net;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
Requires a certificate - see SSLServer file.
Needs to be executed as follows:
java -cp . -Djavax.net.ssl.trustStore=nameOfFile -Djavax.net.ssl.trustStorePassword=password cz.cuni.mff.java.net.SSLClient
 */

public class SSLClient {
    public static void main(String[] args) {
        InetAddress addr;
        try {
            addr = InetAddress.getByName(null);
        } catch (UnknownHostException ex) {
            System.out.println("Unknown host");
            return;
        }

        SocketFactory factory = SSLSocketFactory.getDefault();

        try (Socket socket = factory.createSocket(addr, 6666)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            for (int i = 0; i < 10; i++) {
                out.println(i+"asd");
                String str = in.readLine();
                System.out.println(str);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    /* nothing */
                }
            }
            out.println("END");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
