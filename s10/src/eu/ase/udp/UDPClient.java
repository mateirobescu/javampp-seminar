package eu.ase.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
   public  static void main(String[] args) {
        udpClient(args);
    }

    public static void udpClient(String[] args) {
       try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] buff = new String("What date and time is it?").getBytes();

           InetAddress destAddr = InetAddress.getByName(args[0]);
           int destPort = Integer.parseInt(args[1]);

           DatagramPacket packet =new DatagramPacket(buff, buff.length, destAddr, destPort);
           clientSocket.send(packet);

           byte[] bufResponse = new byte[256];
           DatagramPacket packetReceivedFromServer = new DatagramPacket(bufResponse, bufResponse.length);
           clientSocket.receive(packetReceivedFromServer);

           System.out.println("Client received from server = " + new String(packetReceivedFromServer.getData()));
           clientSocket.close();

       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
