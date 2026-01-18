package airmain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/airmain?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";   // اسم المستخدم
    private static final String PASS = "";       // كلمة السر (خليها فارغة لو ما حطيت باسورد)

    public static Connection getConnection() throws SQLException {
        try {
            // تحميل Driver (بعض الإصدارات تحتاج هذا السطر)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found!", e);
        }

        return DriverManager.getConnection(URL, USER, PASS);
    }
}

