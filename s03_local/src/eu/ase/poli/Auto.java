package eu.ase.poli;

public class Auto extends Vehicle {
    private int doorsNo;

    public Auto() {}

    public Auto(int doorsNo, int weight) throws Exception {
        super(weight);
        if(doorsNo < 0)
            throw new Exception("The doorsNo must not be less than 0.");
        this.doorsNo = doorsNo;
    }

    public int getDoorsNo() {
        return this.doorsNo;
    }

    public void setDoorsNo(int value) throws Exception {
        if(value < 0)
            throw new Exception("The doorsNo must not be less than 0.");
        this.doorsNo = value;
    }

    @Override
    public String display() {
        return new String("Auto - weight: " + this.getWeight() + ", doorsNo: " + this.doorsNo);
    }
}
