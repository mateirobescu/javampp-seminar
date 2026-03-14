package eu.ase.jcf.classes;

import java.util.Comparator;

public class Plane implements Comparable<Plane> {
    private int idPlane;
    private String type;
    private float capacity;

    public Plane() {}

    public Plane(int idPlane, String type, float capacity) {
        this.idPlane = idPlane;
        this.type = type;
        this.capacity = capacity;
    }

    public void print() {
        System.out.println("Plane - id: " + this.idPlane + ", type: " + this.type + ", capacity: " + this.capacity);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Plane)) {
            return false;
        }
        Plane p = (Plane) o;

        return p.idPlane == this.idPlane &&
                p.type.equals(this.type) &&
                Float.floatToIntBits(p.capacity) == Float.floatToIntBits(this.capacity);
    }

    @Override
    public int hashCode() {
        return 31 * 31 *  this.idPlane + 31 * this.type.hashCode() + (int)this.capacity;
    }

    @Override
    public int compareTo(Plane p) {
        if(this.idPlane == p.idPlane) {
            return 0;
        }
        else if (this.idPlane > p.idPlane) {
            return 1;
        } else {
            return -1;
        }
    }
}
