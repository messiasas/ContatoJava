package view;

import dao.ContatoDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import model.Contato;

public class TelaPrincipalTestes extends JFrame {

    private JTextField nomeCamp;
    private JTextField telCamp;
    private JTextField emailCamp;

    private JButton adBtn;
    private JButton atBtn;
    private JButton delBtn;
    private JButton limpBtn;

    ContatoDAO dao;

    public TelaPrincipalTestes(){

        dao = new ContatoDAO();

        setTitle("Contatos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelCamp = new JPanel();
        panelCamp.setLayout(new GridLayout(3,2,5,5));

        panelCamp.add(new JLabel("Nome"));
        nomeCamp = new JTextField();
        panelCamp.add(nomeCamp);

        panelCamp.add(new JLabel("Telefone"));
        telCamp = new JTextField();
        panelCamp.add(telCamp);

        panelCamp.add(new JLabel("Email"));
        emailCamp = new JTextField();
        panelCamp.add(emailCamp);

        JPanel btnPanel = new JPanel();

        adBtn = new JButton("Adicionar");
        atBtn = new JButton("Atualizar");

        delBtn = new JButton("Deletar");
        limpBtn = new JButton("Limpar");

        btnPanel.add(adBtn);
        btnPanel.add(atBtn);
        btnPanel.add(delBtn);
        btnPanel.add(limpBtn);

        add(panelCamp, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        adBtn.addActionListener(e ->{
            String nome = nomeCamp.getText();
            String tel = telCamp.getText();
            String email = emailCamp.getText();

            Contato cont = new Contato(0,nome,tel,email);
            try{
                dao.inserir(cont);
                JOptionPane.showMessageDialog(this,"Contato adicionado.");

            }catch(SQLException s){
                JOptionPane.showMessageDialog(this,"Erro ao adicionar "+s.getMessage());
            }

        });
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() ->{
            TelaPrincipalTestes tela = new TelaPrincipalTestes();
            tela.setVisible(true);
        });
    }
}
