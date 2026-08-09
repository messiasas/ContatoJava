import dao.ConexaoDB;

import java.sql.SQLException;
import java.sql.SQLOutput;

public class TesteConexao {
    public static void main(String[] args){
        try{
            ConexaoDB.getConexao();
            System.out.println("DB connected.");
        } catch (SQLException e) {
            System.out.println("Error to connect: " + e.getMessage());
        }
    }
}
