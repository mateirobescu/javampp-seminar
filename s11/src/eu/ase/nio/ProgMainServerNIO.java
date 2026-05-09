package eu.ase.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ProgMainServerNIO {
    static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        InetSocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 8989);

        serverSocket.bind(serverAddr);
        serverSocket.configureBlocking(false);

        int ops = serverSocket.validOps();
        serverSocket.register(selector, ops, null);

        while (true) {
            System.out.println("I'm a server and I'm waiting for a new connection");
            selector.select();
            Set<SelectionKey> cKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = cKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey mykey = iterator.next();
                if(mykey.isAcceptable()) {
                    SocketChannel sClient = serverSocket.accept();
                    sClient.configureBlocking(false);
                    sClient.register(selector, SelectionKey.OP_READ);
                    System.out.println("Connection accepted: " + sClient.getLocalAddress());
                } else if (mykey.isReadable()) {
                    SocketChannel sclient = (SocketChannel) mykey.channel();
                    ByteBuffer cBuffer = ByteBuffer.allocate(256);

                    sclient.read(cBuffer);
                    String result = new String(cBuffer.array()).trim();
                    System.out.println("Message received: " + result);

                    if(result.equals("Google")) {
                        sclient.close();
                        System.out.println("We'be got the last company name - Google");
                    }
                }

                iterator.remove();
            }
        }
    }
}
