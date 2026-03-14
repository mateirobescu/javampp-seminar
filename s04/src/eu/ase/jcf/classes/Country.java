package eu.ase.jcf.classes;

public class Country {
    public int id;
    public String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void print() {
        System.out.println("Country - id: " + this.id + ", name: " + this.name);
    }
}
