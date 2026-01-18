package airmain.model;

public class Passenger extends Person {

    private Ticket ticket;

    public Passenger(String name, String passportNumber) {
        super(name, passportNumber);
        this.ticket = null;
    }

    // ===================== Getters and Setters =====================
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getPassportNumber() {
        return super.getPassportNumber();
    }

    @Override
    public void setPassportNumber(String passportNumber) {
        super.setPassportNumber(passportNumber);
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    // ===================== Info =====================
    @Override
    public String getInfo() {
        String info = "Passenger -> " + super.getInfo();
        if (ticket != null) {
            info += "   " + ticket.getDetails();
        }
        return info;
    }
}