package eu.ase.io.serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;

public class ObjectRestore {

    public static void main(String[] args) {
        ObjectsGraph og = null;
        try {
            ObjectInputStream sin = new ObjectInputStream(new FileInputStream("test4.txt"));

            og = (ObjectsGraph) sin.readObject();
            System.out.println("og read = " + og);
            URL o3 = (URL)sin.readObject();
            System.out.println("o3 = " + o3);

            boolean exp = ((og.o1 == o3) && (og.o1 == og.o2));
            System.out.println("== " + exp);

            sin.close();
        } catch (Exception e) {
        }
    }
}
