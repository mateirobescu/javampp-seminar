import java.io.*;
import java.util.Objects;

public class Teacher extends Person {
    private String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }


    @Override
    public void saveToFile(String filename) {
        try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            dos.writeUTF(this.getName());
            dos.writeInt(this.getAge());
            dos.writeUTF(this.subject);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getRole() {
        return "Teacher";
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o))
            return false;

        Teacher t = (Teacher) o;
        return this.subject.equals(t.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), this.subject);
    }

    @Override
    public String toString() {
        return "Teacher[%s, subject=%s]".formatted(super.toString(), this.subject);
    }
}
