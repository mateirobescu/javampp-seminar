import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.StringJoiner;

public class Student extends Person {
    private double grade;

    public Student(String name, int age, double grade) {
        super(name, age);
        this.grade = grade;
    }


    @Override
    public void saveToFile(String filename) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            StringJoiner sj = new StringJoiner(",");
            sj.add(this.getName());
            sj.add(String.valueOf(this.getAge()));
            sj.add(String.valueOf(this.grade));

            bw.write(sj.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o))
            return false;

        Student s = (Student) o;
        return Double.compare(this.grade, s.grade) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), this.grade);
    }

    @Override
    public String toString() {
        return "Student[%s, grade=%f]".formatted(super.toString(), this.grade);
    }
}
