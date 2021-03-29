package Java10.src.cz.cuni.mff.java.net;

import java.net.*;

public class InetName {
    public static void main(String[] args) throws Exception {
        InetAddress a = InetAddress.getByName(args[0]);
        System.out.println(a);
        a = InetAddress.getByName(null);
        System.out.println(a);
    }
}

