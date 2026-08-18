package dao;

import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ContatoDAO {

    public void inserir(Contato contato) throws  SQLException{
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?,?,?)";

        try(Connection conn = ConexaoDB.getConnect();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());

            stmt.executeUpdate();
        }
    }

    // List<Contato> porque cada objeto se torna uma lista
   public List<Contato> listarTodos() throws SQLException{
        String sql = "SELECT * FROM contatos ORDER BY nome";

        // crie uma variável chamada contatos, do tipo List que armazena objetos Contato
        List<Contato> contatos = new ArrayList<>();

        try(Connection connect = ConexaoDB.getConnect();
            PreparedStatement stmt = connect.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();)
        {
            while(res.next()){
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");
                String email = res.getString("email");

                // Criando objeto contato que recebe os parametros
                Contato contato = new Contato(id, nome, telefone, email);
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

    public void deletar(int id) throws SQLException{

        String sql = "DELETE FROM contatos WHERE id = ?";

        try(Connection con = ConexaoDB.getConnect();
        PreparedStatement st = con.prepareStatement(sql)){

            st.setInt(1, id);
            st.executeUpdate();
        }
    }

}


































