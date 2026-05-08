import java.util.Locale;
import java.util.Objects;

public abstract class Person implements Storable, Cloneable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public abstract void saveToFile(String filename);

    public abstract String getRole();

    @Override
    public Object clone() {
        try {
            Person p = (Person) super.clone();
//            unnecessary as Strings in java are immutable
//            p.name = new String(this.name);
            return p;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Person[name=%s, age=%d]".formatted(this.name, this.age);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(o == null || this.getClass() != o.getClass())
            return false;

        Person p = (Person) o;

        return this.name.equals(p.name) && this.age == p.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.age);
    }
}
