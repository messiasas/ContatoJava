package view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipalTestes extends JFrame {

    private JTextField compNome;
    private JTextField compTelef;
    private JTextField compEmail;

    private JButton btnAd;
    private JButton btnAt;
    private JButton btnDel;
    private JButton btnLimp;

    public TelaPrincipalTestes(){
        setTitle("Contatos");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel compFrame = new JPanel();
        compFrame.setLayout(new GridLayout(3,2,5,5));

        compFrame.add(new JLabel("Nome:"));
        compNome = new JTextField();
        compFrame.add(compNome);

        compFrame.add(new JLabel("Telefone:"));
        compTelef = new JTextField();
        compFrame.add(compTelef);

        compFrame.add(new JLabel("Email:"));
        compEmail = new JTextField();
        compFrame.add(compEmail);

        JPanel rodaPe = new JPanel();

        btnAd = new JButton("Adicionar");
        btnAt = new JButton("Atualizar");
        btnDel = new JButton("Deletar");
        btnLimp = new JButton("Limpar");

        rodaPe.add(btnAd);
        rodaPe.add(btnAt);
        rodaPe.add(btnDel);
        rodaPe.add(btnLimp);

        add(compFrame, BorderLayout.CENTER);
        add(rodaPe, BorderLayout.SOUTH);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            TelaPrincipalTestes tela = new TelaPrincipalTestes();
            tela.setVisible(true);
        });
    }
}
