package airmain.dao;

import airmain.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DAO {

    // ================= Passenger Queue =================
    private Queue<Passenger> passengerQueue = new LinkedList<>();

    // ================= Passenger Methods =================

    // إضافة إلى DB + Queue
    public void addPassenger(Passenger p) {
        String sql = "INSERT INTO Passenger (name,passportNumber) VALUES (?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setString(2, p.getPassportNumber());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        passengerQueue.add(p);
    }

    // إضافة ركاب فقط للـ Queue (استعمال عند تحميل DB للـ Queue)
    public void addPassengerToQueueOnly(Passenger p) {
        passengerQueue.add(p);
    }

    // قراءة الركاب من الـ Queue
    public Queue<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    // حذف من Queue + DB حسب passportNumber
    public void removePassengerFromQueueAndDB(String passportNumber) {
        Passenger toRemove = null;
        for (Passenger p : passengerQueue) {
            if (p.getPassportNumber().equals(passportNumber)) {
                toRemove = p;
                break;
            }
        }
        if (toRemove != null) {
            passengerQueue.remove(toRemove);

            String sql = "DELETE FROM Passenger WHERE passportNumber=?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, passportNumber);
                stmt.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // تحديث الركاب داخل الـ Queue
    public void updatePassengerQueue(Passenger updated) {
        for (Passenger p : passengerQueue) {
            if (p.getPassportNumber().equals(updated.getPassportNumber())) {
                p.setName(updated.getName()); // ✅ استخدم setter
                break;
            }
        }
    }

    // البحث في الـ Queue حسب passportNumber
    public Passenger searchPassengerByPassport(String passport) {
        for (Passenger p : passengerQueue) {
            if (p.getPassportNumber().equals(passport))
                return p;
        }
        return null;
    }

    // ================= DB-only Passenger Methods =================
    public void updatePassenger(Passenger p) {
        String sql = "UPDATE Passenger SET name=? WHERE passportNumber=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setString(2, p.getPassportNumber());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> list = new ArrayList<>();
        String sql = "SELECT * FROM Passenger";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Passenger p = new Passenger(
                        rs.getString("name"),
                        rs.getString("passportNumber")
                );
                list.add(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // ================= Airport =================
    public void addAirport(Airport airport) {
        String sql = "INSERT INTO airport (name,location,code) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, airport.getName());
            stmt.setString(2, airport.getLocation());
            stmt.setString(3, airport.getCode());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Airport> getAllAirports() {
        List<Airport> list = new ArrayList<>();
        String sql = "SELECT * FROM airport";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Airport(
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("code")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void updateAirport(Airport airport) {
        String sql = "UPDATE airport SET name=?, location=? WHERE code=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, airport.getName());
            stmt.setString(2, airport.getLocation());
            stmt.setString(3, airport.getCode());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAirport(String code) {
        String sql = "DELETE FROM airport WHERE code=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ================= Flight =================
    public void addFlight(Flight flight) {
        String sql = "INSERT INTO Flight (flightNumber,origin,destination,departureTime,arrivalTime,status) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, flight.getFlightNumber());
            stmt.setString(2, flight.getOrigin());
            stmt.setString(3, flight.getDestination());
            stmt.setString(4, flight.getDepartureTime());
            stmt.setString(5, flight.getArrivalTime());
            stmt.setString(6, flight.getStatus());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Flight> getAllFlights() {
        List<Flight> list = new ArrayList<>();
        String sql = "SELECT * FROM Flight";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Flight f = new Flight(
                        rs.getString("flightNumber"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("departureTime"),
                        rs.getString("arrivalTime")
                );
                f.setStatus(rs.getString("status"));
                list.add(f);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void updateFlight(Flight flight) {
        String sql = "UPDATE Flight SET origin=?, destination=?, departureTime=?, arrivalTime=?, status=? WHERE flightNumber=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, flight.getOrigin());
            stmt.setString(2, flight.getDestination());
            stmt.setString(3, flight.getDepartureTime());
            stmt.setString(4, flight.getArrivalTime());
            stmt.setString(5, flight.getStatus());
            stmt.setString(6, flight.getFlightNumber());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateFlightStatus(String flightNumber, String status) {
        String sql = "UPDATE Flight SET status=? WHERE flightNumber=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setString(2, flightNumber);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFlight(String flightNumber) {
        String sql = "DELETE FROM Flight WHERE flightNumber=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, flightNumber);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ================= Employee =================
    public void addEmployee(Employee e) {
        String sql = "INSERT INTO Employee (name,passportNumber,position) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getName());           // ✅ getter
            stmt.setString(2, e.getPassportNumber()); // ✅ getter
            stmt.setString(3, e.getPosition());
            stmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // ================= Ticket =================
    public void addTicket(Ticket t) {
        String sql = "INSERT INTO Ticket (ticketNumber,seatNumber,classType,price,flightNumber,passengerPassport) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getTicketNumber());
            stmt.setString(2, t.getSeatNumber());
            stmt.setString(3, t.getClassType());
            stmt.setDouble(4, t.getPrice());
            stmt.setString(5, t.getFlight().getFlightNumber());
            stmt.setString(6, t.getPassenger().getPassportNumber());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}