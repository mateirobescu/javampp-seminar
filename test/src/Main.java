import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Main {

    static void main() {
        String url = "https://www.bnr.ro/nbrfxrates10days.xml";
        String fileName = "text.xml";

        Path filePath = Path.of(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader("text.xml"))) {
            String res = br.readAllAsString();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new FileReader(fileName)));
            doc.getDocumentElement().normalize();
            NodeList cubeList = doc.getElementsByTagName("Cube");

            Node curr = cubeList.item(0);

            System.out.println(((Element) curr).getAttribute("date"));

            System.out.println(curr.getNodeType());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
