package eu.ase.poli;

public class Vehicle implements Movement, Cloneable {
    private int weight;

    public Vehicle() {}

    public Vehicle(int weight) {
        this.weight = weight;
    }

    public String display() {
        return new String("Vehicle - weight " + this.weight);
    }

    int getWeight() {
        return this.weight;
    }

    @Override
    public void start_engine() {
        System.out.println("Vehicle::startEngine()");
    }

    @Override
    public void stop_engine() {
        System.out.println("Vehicle::stopEngine()");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Vehicle)super.clone();
    }
}
