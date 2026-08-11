package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB{
    private static final String URL = "jdbc:mysql://localhost:3306/agenda_contatos";
    private static final String USER = "root";
    private static final String PIN = "root";

    // Start DB connection
    public static Connection getConnect() throws SQLException{
        return DriverManager.getConnection(URL, USER, PIN);
    }
}
