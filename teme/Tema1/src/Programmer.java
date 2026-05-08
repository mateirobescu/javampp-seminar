public class Programmer extends Employee {
    private int overtimeHours;

    public Programmer(String name, int baseSalary, int overtimeHours) {
        super(name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double computeSalary() {
        return super.computeSalary() + this.overtimeHours * 20;
    }

    @Override
    public String display() {
        return "Programmer[%s, overtimeHours=%d]".formatted(super.display(), this.overtimeHours);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;

        Programmer other = (Programmer) o;

        return this.overtimeHours == other.overtimeHours && super.equals(o);
    }

    @Override
    public int hashCode() {
        return 31 * 31 * this.overtimeHours + super.hashCode();
    }
}
