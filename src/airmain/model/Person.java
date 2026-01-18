package airmain.model;

public class Person {

    private String name;
    private String passportNumber;

    public Person(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    // ================= Getters & Setters =================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    // ================= Info =================
    public String getInfo() {
        return "Name: " + name + " | Passport: " + passportNumber;
    }
}