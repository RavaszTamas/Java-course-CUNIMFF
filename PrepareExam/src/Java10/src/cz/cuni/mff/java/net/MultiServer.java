package Java10.src.cz.cuni.mff.java.net;

import java.net.*;
import java.io.*;

class ServeConnection extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServeConnection(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        try {
            System.out.println("Serving connection");
            while (true) {
                String str = in.readLine();
                if (str.equals("END")) {
                    System.out.println("Connection closing");
                    break;
                }
                out.println(str);
            }
        } catch (IOException e) {
            System.err.println("IO Exception");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}

public class MultiServer {
    public static void main(String[] args) throws IOException {
        int cnt = 0;

        try (ServerSocket s = new ServerSocket(6666)) {
            System.out.println("Server ready");
            while (true) {
                Socket socket = s.accept();
                try {
                    ServeConnection sc = new ServeConnection(socket);
                    sc.start();
                } catch (IOException e) {
                    System.err.println("IO Exception");
                }
                if (cnt++ > 4) {
                    // not to run forever
                    // regular servers run forever
                    break;
                }
            }
        }
    }
}

