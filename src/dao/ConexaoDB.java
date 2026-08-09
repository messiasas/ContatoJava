package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/agenda_contatos";
    private static final String USER = "root";
    private static final String PIN = "root";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PIN);
    }
}
