package airmain.model;

public class Employee extends Person {

    private String position;

    public Employee(String name, String passportNumber, String position) {
        super(name, passportNumber);
        this.position = position;
    }

    @Override
    public String getInfo() {
        return "Employee -> " + super.getInfo() + "  Job: " + position;
    }

    public String getPosition() { return position; }
}

