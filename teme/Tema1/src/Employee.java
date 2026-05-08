public class Employee implements Payable {
    private String name;
    private int baseSalary;

    public Employee(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String display() {
        return "Employee[name=%s, baseSalary=%d]".formatted(this.name, this.baseSalary);
    }

    @Override
    public String toString() {
        return this.display();
    }

    @Override
    public double computeSalary() {
        return baseSalary;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || this.getClass() != o.getClass())
            return false;

        Employee other = (Employee) o;
        return this.name.equals(other.name) && this.baseSalary == other.baseSalary;
    }

    @Override
    public int hashCode() {
        return 31 * this.baseSalary + this.name.hashCode();
    }
}
