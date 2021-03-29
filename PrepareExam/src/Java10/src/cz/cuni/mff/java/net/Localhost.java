package Java10.src.cz.cuni.mff.java.net;

import java.net.*;

public class Localhost {
    public static void main(String[] args) throws Exception {
        System.out.println(InetAddress.getByName(null));
        System.out.println(InetAddress.getLocalHost());
    }
}

