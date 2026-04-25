package eu.ase.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPServer {
    public static void main(String[] args) {
        byte[] bRecv = null;
        byte[] bResp = null;

        try(DatagramSocket socket = new DatagramSocket(7778)) {
            System.out.println("My UDP server is binding in port 7778");
            while (true) {
                bRecv = new byte[256];
                DatagramPacket packet = new DatagramPacket(bRecv, bRecv.length);
                socket.receive(packet);
                System.out.println("UDP client " + packet.getAddress() + ": " + packet.getPort() + " sent to server = " +
                        new String(packet.getData()));

                String respS = null;
                if("What date and time is it?".equals(new String(packet.getData()).trim())) {
                    respS = new Date().toString();
                } else {
                    respS = "I don't understand!";
                }
                bResp = respS.getBytes();
                InetAddress addrSender = packet.getAddress();
                int port = packet.getPort();
                DatagramPacket respPacket = new DatagramPacket(bResp, bResp.length, addrSender, port);
                socket.send(respPacket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
