package eu.ase.poli;

public class Plane extends Vehicle{
    private float capacity;
    private int enginesNo;

    public Plane(int weight, float capacity, int enginesNo) {
        super(weight);
        this.capacity = capacity;
        this.enginesNo = enginesNo;
    }

    @Override
    public String display() {
        return "Plane - weight: " + this.getWeight() + ", capacity: " + this.capacity + ", enginesNo: " + this.enginesNo;
    }

    public void test() {

    }


}
