package eu.ase.oop;

public class Certificate {
    private int id;
    private String name;

    public Certificate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Certificate myClone() {
        return new Certificate(this.getId(), this.getName());
    }

    @Override
    public boolean equals(Object obj) {
        Certificate c = (Certificate) obj;
        return this.id == c.id && this.name.equals(c.name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
