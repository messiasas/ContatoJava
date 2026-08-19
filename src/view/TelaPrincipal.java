package view;

import dao.ContatoDAO;
import model.Contato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

// JFrame é uma classe da biblioteca swing.
public class TelaPrincipal extends JFrame {

    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoEmail;

    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private ContatoDAO dao;

    private int idSelecionado;

    private void atualizarTabela(){
        modeloTabela.setRowCount(0);

        try{
            List<Contato> contatos = dao.listarTodos();
            for(Contato c: contatos){
                Object[] linha = {c.getId(), c.getNome(), c.getTelefone(), c.getEmail()};
                modeloTabela.addRow(linha);
            }
        }catch(SQLException sl){
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: "+sl.getMessage());
        }
    }

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

        /* ================ Tabela contatos ================ */

        modeloTabela = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Telefone");
        modeloTabela.addColumn("Email");

        tabela = new JTable(modeloTabela);

        JScrollPane scrollTabela = new JScrollPane(tabela);

        // Todo JTable tem um "modelo de seleção" (SelectionModel)
        tabela.getSelectionModel().addListSelectionListener(e -> {
            int linhaSelec = tabela.getSelectedRow();

            // Devolve o índice da linha atualmente selecionada (0, 1, 2...).
            // Se nada estiver selecionado, devolve -1
            if(linhaSelec >= 0){
                idSelecionado = (int) modeloTabela.getValueAt(linhaSelec, 0); // linha x colunas
                campoNome.setText((String) modeloTabela.getValueAt(linhaSelec, 1));
                campoTelefone.setText((String) modeloTabela.getValueAt(linhaSelec,2));
                campoEmail.setText((String) modeloTabela.getValueAt(linhaSelec,3)); // linha x colunas
            }
        });

        /* ================ Painel Botões ================ */

        JPanel painelBtn = new JPanel();

        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        btnAdicionar.addActionListener(e ->{
            String nome = campoNome.getText();
            String telef = campoTelefone.getText();
            String email = campoEmail.getText();

            Contato cont = new Contato(0,nome,telef,email);

            try{
                dao.inserir(cont);
                JOptionPane.showMessageDialog(this,"Contato adicionado.");
                atualizarTabela();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(this, "Erro ao adicionar: "+ex.getMessage());
            }
        });

        btnAtualizar.addActionListener(e -> {
            String nome = campoNome.getText();
            String telef = campoTelefone.getText();
            String email = campoEmail.getText();

            Contato contatoAt = new Contato(0,nome,telef,email);

            try{
                dao.atualizar(contatoAt);
                JOptionPane.showMessageDialog(this, "Contato "+nome+" atualizado.");
                atualizarTabela();
            }catch(SQLException ss){
                JOptionPane.showMessageDialog(this,"Falha ao atualizar dados"+ss.getMessage());
            }
        });

        painelBtn.add(btnAdicionar);
        painelBtn.add(btnAtualizar);
        painelBtn.add(btnExcluir);
        painelBtn.add(btnLimpar);

        add(painelFormulario, BorderLayout.NORTH);
        add(scrollTabela, BorderLayout.CENTER);
        add(painelBtn, BorderLayout.SOUTH);

        atualizarTabela();
    }

    // O formato (() -> {}) é chamada "expressao lambda", uma forma mais compacta de escrever sem precisar criar classe separada
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }

}
