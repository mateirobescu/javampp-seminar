public class ProgMain {

    public static void main(String[] args) {
        Employee[] emps = {
                new Manager("Alice Smith", 80000, 15000),
                new Manager("Bob Jones", 85000, 12000),
                new Manager("Charlie Brown", 90000, 20000),

                new Programmer("Diana Prince", 70000, 10),
                new Programmer("Evan Wright", 72000, 5),
                new Programmer("Fiona Glenanne", 75000, 20),

                new Employee("George Costanza", 50000),
                new Employee("Harry Potter", 55000),
                new Employee("Ian Malcolm", 60000)
        };

        for(Employee emp : emps)
            System.out.println(emp.display() + " " + emp.computeSalary());

        Programmer p1 = new Programmer("Ada Lovelace", 95000, 40);
        Programmer p2 = new Programmer("Ada Lovelace", 95000, 40);

        System.out.println("Comparing references: " + (p1 == p2));
        System.out.println("Comparing objects: " + p1.equals(p2));

        if(p1 instanceof Employee emp)
            System.out.println(emp.computeSalary());

        if(p1 instanceof Payable pyb)
            System.out.println(pyb.computeSalary());


    }
}
