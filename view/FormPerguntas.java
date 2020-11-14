package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.InsetsUIResource;

import model.MnDB;
import model.Questao;

public class FormPerguntas extends JPanel {
    private AppFrame frame;

    private static final Insets FIELD_INSETS = new InsetsUIResource(5, 10, 0, 0);
    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField idTxt;
    private JTextField perguntaTxt;
    private JTextField altTxt;
    private JTextField alt2Txt;
    private JTextField alt3Txt;
    private JTextField alt4Txt;
    private JButton salvarBtn;
    private JButton cancelarBtn;

    public FormPerguntas(AppFrame appFrame) {
        this.frame = appFrame;
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        // Zerar campos do formulario.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                perguntaTxt.setText("");
                altTxt.setText("");
                alt2Txt.setText("");
                alt3Txt.setText("");
                alt4Txt.setText("");
            }
        });

        criarForm();
    }

    private void criarForm() {
        JLabel rotulo;

        rotulo = new JLabel("Id");
        addComponente(rotulo, 0, 0);
        idTxt = new JTextField(30);
        idTxt.setEditable(false);
        addComponente(idTxt, 0, 1);

        rotulo = new JLabel("Pergunta");
        addComponente(rotulo, 1, 0);
        perguntaTxt = new JTextField(30);
        perguntaTxt.setDocument(new LimitChar(100));
        addComponente(perguntaTxt, 1, 1);
        rotulo = new JLabel("Primeira Alternativa");
        addComponente(rotulo, 2, 0);
        altTxt = new JTextField(30);
        altTxt.setDocument(new LimitChar(100));
        addComponente(altTxt, 2, 1);

        rotulo = new JLabel("Segunda Alternativa");
        addComponente(rotulo, 3, 0);
        alt2Txt = new JTextField(30);
        alt2Txt.setDocument(new LimitChar(100));
        addComponente(alt2Txt, 3, 1);

        rotulo = new JLabel("Terceira Alternativa");
        addComponente(rotulo, 4, 0);
        alt3Txt = new JTextField(30);
        alt3Txt.setDocument(new LimitChar(100));
        addComponente(alt3Txt, 4, 1);

        rotulo = new JLabel("Quarta Alternativa");
        addComponente(rotulo, 5, 0);
        alt4Txt = new JTextField(30);
        alt4Txt.setDocument(new LimitChar(100));
        addComponente(alt4Txt, 5, 1);

        criarBtn();
    }

    private void criarBtn() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        saveBtn();
        btnPanel.add(salvarBtn);
        cancelBtn();
        btnPanel.add(cancelarBtn);

        addComponente(btnPanel, 7, 1, 2, 1);

    }

    private void saveBtn() {
        salvarBtn = new JButton("Salvar");
        salvarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Questao questao = new Questao();
                if (perguntaTxt.getText().length() > 0) {
                    questao.setPergunta(perguntaTxt.getText());
                } // validação de campo vazio
                else if (altTxt.getText().length() > 0) {
                    questao.setAlternativa(altTxt.getText());
                } // validação de campo vazio
                else if (alt2Txt.getText().length() > 0) {
                    questao.setAlternativa2(alt2Txt.getText());
                } // validação de campo vazio
                else if (alt3Txt.getText().length() > 0) {
                    questao.setAlternativa3(alt3Txt.getText());
                } // validação de campo vazio
                else if (alt4Txt.getText().length() > 0) {
                    questao.setAlternativa4(alt4Txt.getText());
                } // validação de campo vazio

                if ((perguntaTxt.getText().length() > 0) && (altTxt.getText().length() > 0)
                        && (alt2Txt.getText().length() > 0) && (alt3Txt.getText().length() > 0)
                        && (alt4Txt.getText().length() > 0)) {
                    JOptionPane.showMessageDialog(FormPerguntas.this, "Questão criada com sucesso!", AppFrame.TITULO,
                            JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
                    MnDB.inserir(questao);// insere a questão no "Banco de dados"
                    frame.mostrarPerguntas();// volta para a lista de perguntas
                } else {
                    JOptionPane.showMessageDialog(FormPerguntas.this,
                            "Voce esqueceu de preencher um campo, verifique e tente novamente!", AppFrame.TITULO,
                            JOptionPane.ERROR_MESSAGE);// mensagem de erro par alertar o usuário que tem campos vazios
                }
            }

        });
    }

    private void cancelBtn() {
        cancelarBtn = new JButton("Cancelar");
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarPerguntas();// volta para a lista de Perguntas
            }
        });
    }

    private void addComponente(JComponent rotulo, int linha, int coluna) {

        addComponente(rotulo, linha, coluna, 1, 1);
    }

    private void addComponente(JComponent componente, int linha, int coluna, int largura, int altura) {
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = FIELD_INSETS;

        layout.setConstraints(componente, constraints);

        add(componente);

    }

}
