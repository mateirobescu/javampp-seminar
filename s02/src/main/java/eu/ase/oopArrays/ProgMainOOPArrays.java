package eu.ase.oopArrays;

import java.util.SortedMap;

public class ProgMainOOPArrays {
    public static void main() {
        Student[] students = new Student[2];

        students[0] = new Student("S0", new short[]{5,5,9});
        students[1] = new Student("S1", new short[]{9, 10, 9});

        for(int i = 0; i < students.length; i++)
            System.out.println("Avg mark for " + i + " is " + students[i].getAvgMark());

        System.out.println(students[0].toString());
        System.out.println(students[0]);
        Student s3 = new Student("S0", new short[]{5,5,9});
        System.out.println(" == " + (students[0] == s3));
        System.out.println(" == " + students[0].equals(s3));
        System.out.println("s3 hashcode " + s3.hashCode());
        System.out.println("s0 hashcode " + students[0].hashCode());

        try {
            Student clone = (Student) students[0].clone();
            System.out.println(clone);
            System.out.println(" == " + (students[0] == clone));
            System.out.println(" equals "  + students[0].equals(clone));
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
    }
}
