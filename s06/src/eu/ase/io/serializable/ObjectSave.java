package eu.ase.io.serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

public class ObjectSave {
    static void main() {
        ObjectsGraph og = null;

        try {
            ObjectOutputStream sout = new ObjectOutputStream(new FileOutputStream("test4.txt"));
            URL o1 = new URL("http://www.google.com");
            URL o2 = o1;
            URL o3 = o1;

            og = new ObjectsGraph(o1, o2);
            sout.writeObject(og);
            sout.writeObject(o3);

            sout.flush();
            sout.close();

            boolean exp = ((og.o1 == o3) && (og.o1 == og.o2));
            System.out.println("== " + exp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
