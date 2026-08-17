package view;

import javax.swing.*;
import java.awt.*;

// JFrame é uma classe da biblioteca swing.
public class TelaPrincipal extends JFrame {

    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoEmail;

    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    public TelaPrincipal(){

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
