package airmain.model;

public class Ticket {

    private String ticketNumber;
    private String seatNumber;
    private String classType;
    private double price;
    private Flight flight;
    private Passenger passenger;

    public Ticket(String ticketNumber, String seatNumber, String classType, double price,
                  Flight flight, Passenger passenger) {
        this.ticketNumber = ticketNumber;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.price = price;
        this.flight = flight;
        this.passenger = passenger;
    }

    public String getDetails() {
        return "TicketNumber: " + ticketNumber
                + " Seat: " + seatNumber
                + " Class: " + classType
                + " Price: " + price
                + " Flight: " + flight.getFlightNumber();
    }

    public String getTicketNumber() { return ticketNumber; }
    public String getSeatNumber() { return seatNumber; }
    public String getClassType() { return classType; }
    public double getPrice() { return price; }
    public Flight getFlight() { return flight; }
    public Passenger getPassenger() { return passenger; }
}

