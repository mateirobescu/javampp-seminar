package eu.ase.io.serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;

public class ObjectRestore {

    public static void main(String[] args) {
        ObjectsGraph og = null;
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test4.txt"));
//
//            og = (ObjectsGraph) ois.readObject();
//            System.out.println("og read = " + og);
//            URL o3 = (URL)ois.readObject();
//            System.out.println("o3 = " + o3);
//
//            boolean exp = ((og.o1 == o3) && (og.o1 == og.o2));
//            System.out.println("== " + exp);
//
//            ois.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test4.txt"));) {

            og = (ObjectsGraph) ois.readObject();
            System.out.println("og read = " + og);
            URL o3 = (URL)ois.readObject();
            System.out.println("o3 = " + o3);

            boolean exp = ((og.o1 == o3) && (og.o1 == og.o2));
            System.out.println("== " + exp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (MyResource r= new MyResource()) {
            r.doSomething();
        }

        try {
            int v = 10 / 0;
        } catch (Exception e) {
            System.out.println("catch: " + e.getMessage());
        } finally {
            System.out.println("finally");
        }
        System.out.println("dupa");

    }
}
