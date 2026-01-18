package airmain.model;

import java.util.ArrayList;
import java.util.List;

public class Airport {

    private String name;
    private String location;
    private String code;
    private List<Flight> flights;

    public Airport(String name, String location, String code) {
        this.name = name;
        this.location = location;
        this.code = code;
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void listFlights() {
        if (flights.isEmpty()) {
            System.out.println("There are no flights connected to this Airport.");
        } else {
            for (Flight f : flights) {
                System.out.println(f.getDetails());
            }
        }
    }

    public String getInfo() {
        return "Airport: " + name + " (" + code + "), location: " + location;
    }

    // ⭐ الحل هنا
    public String getDetails() {
        return getInfo();
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getCode() { return code; }
    public List<Flight> getFlights() { return flights; }
}