import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Alice", 20, 9.5);
        Student s2 = new Student("Bob", 22, 7.3);
        Student s3 = new Student("Bob", 22, 7.3);

        Teacher t1 = new Teacher("Mr. Smith", 45, "Math");
        Teacher t2 = new Teacher("Mr. Smith", 45, "Math");
        Teacher t3 = new Teacher("Dr. Brown", 52, "Chemistry");

        Repository<Person> rep = new Repository<>();

        rep.add(s1);
        rep.add(s2);
        rep.add(s3);
        rep.add(t1);
        rep.add(t2);
        rep.add(t3);
        for(Person p : rep.getAll())
            System.out.println(p);

        rep.remove(s1);
        rep.remove(t2);
        System.out.println("\n");
        for(Person p : rep.getAll())
            System.out.println(p);

        System.out.println("\n");
        System.out.println(rep.findByIndex(0));
        System.out.println(rep.findByIndex(10));

        System.out.println("\n");
        for(Person p : rep.getAll())
            System.out.println(p.getRole());

        Set<Person> set = new HashSet<>();
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(t1);
        set.add(t2);
        set.add(t3);
        System.out.println("\n");
        for(Person p : set)
            System.out.println(p);

        Student s4 = (Student) s3.clone();
        System.out.println("\n == " + (s3 == s4));
        System.out.println(" equals " + s3.equals(s4));

        s1.saveToFile("test1.txt");
        t1.saveToFile("test1.bin");

    }
}
