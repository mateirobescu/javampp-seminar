package eu.ase.httpserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HTTPSeminarProtocol {
    public String processInput(String theInput) {
        String theOutput = "";
        byte[] buffResponse = new byte[4096];
        // GET /indextest.html HTTP/1.1

        if(theInput.indexOf("GET") != 0) {
            theOutput = "HTTP/1.1 200 OK\r\nContent-Length: 19\r\nNU STIU COMANDA\r\n\r\n";
        } else {
            String fileName = theInput.substring(theInput.indexOf("/") + 1, theInput.indexOf(" HTTP/1.1"));
            String fileExt = fileName.substring(fileName.indexOf(".") + 1);

            String contentType = "";
            String fileContent = "";

            if(fileExt.compareToIgnoreCase("txt") == 0) {
                contentType = "text/html";
            } else if(fileExt.compareToIgnoreCase("html") == 0) {
                contentType = "text/html";
            } else if (fileExt.compareToIgnoreCase("gif") == 0) {
                contentType = "image/gif";
            }

            try {
                FileInputStream fis = new FileInputStream(fileName);
                int bRead = 0;

                while ((bRead = fis.read(buffResponse)) != -1) {
                    fileContent += new String(buffResponse, 0, bRead);
                }

                fis.close();;
                theOutput = "HTTP/1.1 200 OK\n" +
                        "Content-Length: " + fileContent.length() + "\r\nContent-Type: "
                        + contentType + "\r\n\r\n" + fileContent + "\r\n ";

            } catch (IOException e) {
                theOutput = "HTTP/1.1 404\r\n\r\n";
            }
        }

        return theOutput;
    }
}
