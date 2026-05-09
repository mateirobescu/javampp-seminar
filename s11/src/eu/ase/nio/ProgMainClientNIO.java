package eu.ase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class ProgMainClientNIO {
    static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 8989);
        SocketChannel myClient = SocketChannel.open(serverAddr);
        System.out.println("Connecting to server o port 8989");

        ArrayList<String> companyDetails = new ArrayList<>();
        companyDetails.add("Facebook");
        companyDetails.add("IBM");
        companyDetails.add("Twitter");
        companyDetails.add("Google");

        for(String companyName : companyDetails) {
            byte[] message = new String(companyName).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);

            myClient.write(buffer);
            System.out.println("Sending: " + companyName);

            buffer.clear();
            Thread.sleep(2000);
        }

        myClient.close();
    }
}
