package dao;

import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAOT {

    public void inserir(Contato contato) throws SQLException{
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";

        try(Connection connection = ConexaoDB.getConnect();
            PreparedStatement st = connection.prepareStatement(sql)){

            st.setString(1, contato.getNome());
            st.setString(2, contato.getTelefone());
            st.setString(3, contato.getEmail());

            st.executeUpdate();
        }
    }

    public List<Contato> buscarTodos() throws SQLException{
        String sql = "SELECT * FROM contatos ORDER BY nome";

        List<Contato> contatos = new ArrayList<>();

        try(Connection connection = ConexaoDB.getConnect();
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery()){

            while(rs.next()){

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");

                Contato contato = new Contato(id,nome,telefone,email);
                contatos.add(contato);
            }
        }

        return contatos;
    }

    public void atualizar(Contato contato) throws SQLException{
        String sql = "UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE id = ?";

        try(Connection connection = ConexaoDB.getConnect();
        PreparedStatement st = connection.prepareStatement(sql)){

            st.setString(1, contato.getNome());
            st.setString(2, contato.getTelefone());
            st.setString(3, contato.getEmail());
            st.setInt(4, contato.getId());

            st.executeUpdate();
        }
    }

    public void atualizar(int id) throws SQLException{
        String sql = "DELETE FROM contatos WHERE id = ?";

        try(Connection connection = ConexaoDB.getConnect();
        PreparedStatement st = connection.prepareStatement(sql)){

            st.setInt(1, id);
            st.executeUpdate();

        }
    }
}
