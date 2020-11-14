package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.MnDB;

public class ListaPerguntas extends JPanel {
    private AppFrame frame;
    private JButton voltarBtn;
    private JButton novaBtn;
    private JButton editarBtn;
    private JButton deletarBtn;
    private JTable tabela;
    private static PerguntaTableModel tableModel;

    public ListaPerguntas(AppFrame appFrame) {
        setLayout(new BorderLayout(10, 10));

        this.frame = appFrame;

        criarBotoes();
        criarTabela();

    }

    public static void recarregar() {
        tableModel.carregar(MnDB.listar());
    }

    private void criarBotoes() {
        JPanel panel = new JPanel();
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(FlowLayout.RIGHT);

        voltarBtn = new JButton("Voltar");
        voltarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.voltar();
            }
        });

        panel.add(voltarBtn);// Botão voltar

        novaBtn = new JButton("Criar Pergunta");
        novaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFormPerguntas();
            }
        });
        panel.add(novaBtn);// Criar Pergunta

        editarBtn = new JButton("Editar Pergunta");
        editarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFormPerguntas();
            }
        });
        panel.add(editarBtn);// Editar Pergunta

        deletarBtn = new JButton("Deletar Pergunta");
        panel.add(deletarBtn);// Deletar Pergunta

        add(panel, BorderLayout.NORTH);

        desabilitarBtn();
    }

    private void criarTabela() {
        tableModel = new PerguntaTableModel(MnDB.listar());

        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (tabela.getSelectedRow() >= 0) {
                        habilitarBtn();
                    } else {
                        desabilitarBtn();
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void desabilitarBtn() {
        editarBtn.setEnabled(false);
        deletarBtn.setEnabled(false);
    }

    private void habilitarBtn() {
        editarBtn.setEnabled(true);
        deletarBtn.setEnabled(true);
    }
}
