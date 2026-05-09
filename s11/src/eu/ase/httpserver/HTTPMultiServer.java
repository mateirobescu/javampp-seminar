package eu.ase.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPMultiServer {
    static void main(String[] args) {
        ServerSocket serverSocket = null;
        boolean listening = true;

        int port = Integer.parseInt(args[0]);
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listens in port " + port);
            while (listening){
                Socket client = serverSocket.accept();
                HTTPMultiServerThread objClient = new HTTPMultiServerThread(client);
                objClient.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
