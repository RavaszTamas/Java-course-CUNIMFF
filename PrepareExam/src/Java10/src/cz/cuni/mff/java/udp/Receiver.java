package Java10.src.cz.cuni.mff.java.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
    public static void main(String args[]) {
        try (DatagramSocket socket = new DatagramSocket(6666)) {
            byte[] receiveData = new byte[4];
            int maxReceived = 0;
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                int receivedInt = bytesToInt(receiveData);
                System.out.println("Received " + receivedInt + " from " + receivePacket.getAddress());
                if (receivedInt == 0) {
                    break;
                }

                if (receivedInt - maxReceived != 1) {
                    System.out.println("WARNING: something bad happened");
                }
                maxReceived = receivedInt;
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static int bytesToInt(byte[] data) {
        return (0xff & data[0]) << 24 |
                (0xff & data[1]) << 16 |
                (0xff & data[2]) << 8  |
                (0xff & data[3]);
    }
}
