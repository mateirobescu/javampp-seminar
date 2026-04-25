package eu.ase.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Instant;

public class MulticastUDPServer {
    static void main(String[] args) {
        DatagramSocket socket = null;
        boolean running = true;
        byte[] buf = null;

        try {
            socket = new DatagramSocket(Integer.parseInt(args[0]));
            System.out.println("Multicast UDP server started and bind on port " + args[0]);
            while (running) {
                String dString = Instant.now().toString();
                buf = dString.getBytes();
                InetAddress group = InetAddress.getByName("230.0.0.1");

                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);
                try {
                    Thread.sleep((long) (Math.random() * 5000) );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
