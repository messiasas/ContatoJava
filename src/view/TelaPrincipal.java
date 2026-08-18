package view;

import dao.ContatoDAO;
import model.Contato;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

// JFrame é uma classe da biblioteca swing.
public class TelaPrincipal extends JFrame {

    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoEmail;

    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    private ContatoDAO dao;

    public TelaPrincipal(){
        dao = new ContatoDAO();
        // Como herdamos a herança, nós já temos os metodos sem precisar inicializar

        setTitle("Agenda de contatos");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridLayout(3, 2, 5, 5));

        painelFormulario.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painelFormulario.add(campoNome);

        painelFormulario.add(new JLabel("Telefone"));
        campoTelefone = new JTextField();
        painelFormulario.add(campoTelefone);

        painelFormulario.add(new JLabel("Email:"));
        campoEmail = new JTextField();
        painelFormulario.add(campoEmail);

        JPanel painelBtn = new JPanel();

        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        btnAdicionar.addActionListener(e ->{
            String nome = campoNome.getText();
            String telef = campoTelefone.getText();
            String email = campoTelefone.getText();

            Contato cont = new Contato(0,nome,telef,email);

            try{
                dao.inserir(cont);
                JOptionPane.showMessageDialog(this,"Contato adicionado.");
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(this, "Erro ao adicionar: "+ex.getMessage());
            }
        });

        painelBtn.add(btnAdicionar);
        painelBtn.add(btnAtualizar);
        painelBtn.add(btnExcluir);
        painelBtn.add(btnLimpar);

        add(painelFormulario, BorderLayout.CENTER);
        add(painelBtn, BorderLayout.SOUTH);
    }

    // O formato (() -> {}) é chamada "expressao lambda", uma forma mais compacta de escrever sem precisar criar classe separada
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }

}
