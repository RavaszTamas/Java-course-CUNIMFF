package Java10.src.cz.cuni.mff.java.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

    public static void main(String args[]) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost");

            byte[] sendData;

            for (int i = 1; i < 1000; i++) {
                sendData = intToBytes(i);
                System.out.println("Sending " + i);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 6666);
                socket.send(sendPacket);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            }
            sendData = intToBytes(0);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 6666);
            socket.send(sendPacket);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static byte[] intToBytes(final int data) {
        return new byte[] {
                (byte)((data >> 24) & 0xff),
                (byte)((data >> 16) & 0xff),
                (byte)((data >> 8) & 0xff),
                (byte)((data >> 0) & 0xff),
        };
    }

}
