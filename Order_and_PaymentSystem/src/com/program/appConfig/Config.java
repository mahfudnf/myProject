package com.program.appConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static Connection connection;

    public static Connection myConnection(){
        try {
            if (connection == null || connection.isClosed()) {
                // KONEKSI KE DATABASE
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/order_payment_system", "root", "dataku@12");
                System.out.println("CONNECTION SUCCESSS");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("CONNECTION FAILED " + e.getMessage());
        }
        return connection;
    }
}
