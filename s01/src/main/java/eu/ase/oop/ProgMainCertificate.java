package eu.ase.oop;

public class ProgMainCertificate {

    public static void main(String[] args) {
        Certificate c1 = new Certificate(777, "c1");
        Certificate c2 = new Certificate(999, "c2");


        System.out.println(c1);
        System.out.println("c1: " + c1.getId() + " " + c1.getName());

        c2 = c1;
        System.out.println(c2);
        System.out.println("c2: " + c2.getId() + " " + c2.getName());

        Certificate c3 = c1.myClone();
        System.out.println(c3);
        System.out.println("c3: " + c3.getId() + " " + c3.getName());

        boolean b = c1.equals(c3);
        System.out.println(b);
        String s = "ofsf";
//        boolean b1 = c1.equals(s);
//        System.out.println(b1);

    }
}
