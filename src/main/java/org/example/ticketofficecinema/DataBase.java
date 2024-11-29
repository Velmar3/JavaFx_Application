package org.example.ticketofficecinema;

import java.sql.Connection;  // Используйте правильный импорт
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbookcinema", "root", "");
            return connect;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}