public class Manager extends Employee {
    private int bonus;

    public Manager(String name, int baseSalary, int bonus) {
        super(name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double computeSalary() {
        return super.computeSalary() + this.bonus;
    }

    @Override
    public String display() {
        return "Manager[%s, overtimeHours=%d]".formatted(super.display(), this.bonus);
    }
}