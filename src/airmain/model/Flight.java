package airmain.model;

import java.util.ArrayList;
import java.util.List;

public class Flight {

    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String status;
    private List<Passenger> passengers;

    public Flight(String flightNumber, String origin, String destination,
                  String departureTime, String arrivalTime) {

        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = "On Time"; // القيمة الافتراضية
        this.passengers = new ArrayList<>();
    }

    // ================= Flight details =================
    public String getDetails() {
        return "Flight " + flightNumber +
                " | From: " + origin +
                " | To: " + destination +
                " | Departure: " + departureTime +
                " | Arrival: " + arrivalTime +
                " | Status: " + status;
    }

    // ================= Setters =================
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public void setOrigin(String origin) { this.origin = origin; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public void setStatus(String status) { this.status = status; }

    // ================= Getters =================
    public String getFlightNumber() { return flightNumber; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public String getStatus() { return status; }

    // ================= Passenger handling =================
    public void addPassenger(Passenger p) {
        passengers.add(p);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}