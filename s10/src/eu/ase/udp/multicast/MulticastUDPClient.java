package eu.ase.udp.multicast;

import java.io.IOException;
import java.net.*;

public class MulticastUDPClient {
    static void main(String[] args) {
        try {
            MulticastSocket socket = new MulticastSocket(Integer.parseInt(args[0]));
            // Macos only
            NetworkInterface ni = NetworkInterface.getByName("en0");
            InetAddress address = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(new InetSocketAddress(address, 4446), ni);

//            Other os
//            socket.joinGroup(address);

            DatagramPacket packet;
            for(int i = 0; i < 5; i++) {
                byte[] buff = new byte[256];
                packet = new DatagramPacket(buff, buff.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received multicast: " + received);
            }
            // Other os
//            socket.leaveGroup(address);

            // Macos only
            socket.leaveGroup(new InetSocketAddress(address, 4446), ni);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
