package tests;

import model.Contato;
import dao.ContatoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TesteDAO {
    public static void main(String[] args) throws SQLException {

        ContatoDAO dao = new ContatoDAO();
       /* Contato novoContato = new Contato(0,"Messias","81019289239","messias@gmail.com");

        dao.inserir(novoContato);
        System.out.println("Register name - "+ novoContato.getNome() + " - created.");*/

        List<Contato> contatos = dao.listarTodos();
        for(Contato c: contatos){
            System.out.println("ID: "+c.getId()+" | Name: "+c.getNome()+" | Tel: "+c.getTelefone()+" | Email: "+c.getEmail()+".");
        }

    }
}
