package airmain.gui;

import airmain.dao.DAO;
import airmain.model.Flight;

public class AirportDBsystem {

    public static void main(String[] args) {

        DAO dao = new DAO();

        // إنشاء رحلة
        Flight f1 = new Flight(
                "F100",
                "Sana'a",
                "Cairo",
                "10:00",
                "On Time"
        );

        // تحديد الحالة
        f1.setStatus("Delayed");

        // إضافة
        dao.addFlight(f1);

        // عرض (للتجربة)
        dao.getAllFlights();

        // تعديل
        dao.updateFlight(f1);

        // حذف
        dao.deleteFlight("F100");

        System.out.println("Flight CRUD operations completed successfully.");
    }
}