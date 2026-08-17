package tests;

import jdk.swing.interop.SwingInterOpUtils;
import model.Contato;
import dao.ContatoDAO;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class TesteDAO {
    public static void main(String[] args) throws SQLException {

        ContatoDAO dao = new ContatoDAO();
        Contato newCont = new Contato(0,"Filipe","1192201920","filipe@gmail.com");

        dao.inserir(newCont);

        List<Contato> contatos = dao.listarTodos();
        for(Contato c: contatos){
            System.out.println("ID: "+c.getId()+" | Name: "+c.getNome()+" | Telefone: "+c.getTelefone()+" | Email: "+c.getEmail());
        }

        Contato contId = contatos.get(0);
        contId.setNome("Messias");
        dao.atualizar(contId);

        System.out.println();

        List<Contato> contatosAtualiz = dao.listarTodos();

        System.out.println("Atualizado: ");
        for(Contato c: contatosAtualiz){
            System.out.println("ID: "+c.getId()+" | Name: "+c.getNome()+" | Telefone: "+c.getTelefone()+" | Email: "+c.getEmail());
        }

        int delId = contId.getId();
        dao.deletar(delId);

        System.out.println();

        List<Contato> contatosAt = dao.listarTodos();
        System.out.println("Contato "+contId.getNome()+" deletado.");
        for(Contato c: contatosAt){
            System.out.println("ID: "+c.getId()+" | Name: "+c.getNome()+" | Telefone: "+c.getTelefone()+" | Email: "+c.getEmail());
        }






















    }
}

