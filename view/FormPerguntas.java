package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.InsetsUIResource;

import model.MnDB;
import model.Questao;
import model.QuestaoDificil;
import model.QuestaoFacil;
import model.QuestaoMedio;

public class FormPerguntas extends JPanel {
    private AppFrame frame;

    private static final Insets FIELD_INSETS = new InsetsUIResource(5, 10, 0, 0);
    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField idTxt;
    private JTextField perguntaTxt;
    private JTextField resposta;
    private JTextField altTxt;
    private JTextField alt2Txt;
    private JTextField alt3Txt;
    private JTextField alt4Txt;
    private JButton salvarBtn;
    private JButton cancelarBtn;

    private JRadioButton facil;
    private JRadioButton medio;
    private JRadioButton dificil;

    private ButtonGroup grupo1;

    private Questao questao;

    public FormPerguntas(AppFrame appFrame) {
        this.frame = appFrame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        questao = null;

        setLayout(layout);
        // Zerar campos do formulario.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (questao == null) {
                    idTxt.setText("");
                    perguntaTxt.setText("");
                    resposta.setText("");
                    altTxt.setText("");
                    alt2Txt.setText("");
                    alt3Txt.setText("");
                    alt4Txt.setText("");
                    grupo1.clearSelection();
                } else {
                    idTxt.setText(Integer.toString(questao.getId()));
                    perguntaTxt.setText(questao.getPergunta());
                    resposta.setText(questao.getResposta());
                }
                if (questao.getDificuldade() == "Fácil") {
                    facil.setSelected(true);
                    altTxt.setText(questao.getAlternativa());
                    alt2Txt.setText(questao.getAlternativa2());
                } else if (questao.getDificuldade() == "Médio") {
                    medio.setSelected(true);
                    altTxt.setText(questao.getAlternativa());
                    alt2Txt.setText(questao.getAlternativa2());
                    alt3Txt.setText(questao.getAlternativa3());
                } else {
                    dificil.setSelected(true);
                    altTxt.setText(questao.getAlternativa());
                    alt2Txt.setText(questao.getAlternativa2());
                    alt3Txt.setText(questao.getAlternativa3());
                    alt4Txt.setText(questao.getAlternativa4());
                }

            }
        });

        criarForm();
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
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

        rotulo = new JLabel("Resposta");
        addComponente(rotulo, 2, 0);
        resposta = new JTextField(30);
        resposta.setDocument(new LimitChar(100));
        addComponente(resposta, 2, 1);

        rotulo = new JLabel("Selecione a dificuldade da questão:");
        addComponente(rotulo, 3, 0);
        colocarRadio();

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

        addComponente(btnPanel, 8, 1, 2, 1);

    }

    private void saveBtn() {
        salvarBtn = new JButton("Salvar");
        salvarBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (facil.isSelected()) {
                    Questao q = new QuestaoFacil();
                    q.criarQuestao(perguntaTxt, resposta, altTxt, alt2Txt);

                } else if (medio.isSelected()) {
                    Questao q = new QuestaoMedio();
                    q.criarQuestao(perguntaTxt, resposta, altTxt, alt2Txt, alt3Txt);
                } else if (dificil.isSelected()) {
                    Questao q = new QuestaoDificil();
                    q.criarQuestao(perguntaTxt, resposta, altTxt, alt2Txt, alt3Txt, alt4Txt);
                }
                if (perguntaTxt != null && resposta != null && altTxt != null && alt2Txt != null) {
                    JOptionPane.showMessageDialog(FormPerguntas.this,
                            "Voce esqueceu de preencher um campo, verifique e tente novamente!", AppFrame.TITULO,
                            JOptionPane.ERROR_MESSAGE);// mensagem de erro par alertar o usuário que tem campos vazios
                }
                if (idTxt.getText().isBlank()) {
                    MnDB.inserir(questao);// insere a questão no "Banco de dados"
                    JOptionPane.showMessageDialog(FormPerguntas.this, "Questão criada com sucesso!", AppFrame.TITULO,
                            JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
                } else {
                    questao.setId(Integer.parseInt(idTxt.getText()));
                    MnDB.atualizar(questao);// atualiza a questão no "Banco de dados"
                    JOptionPane.showMessageDialog(FormPerguntas.this, "Questão Editada com sucesso!", AppFrame.TITULO,
                            JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
                }

                frame.mostrarPerguntas();// volta para a lista de perguntas

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

    private void colocarRadio() {
        JPanel painelRadio = new JPanel();

        facil = new JRadioButton("Fácil", false);
        medio = new JRadioButton("Médio", false);
        dificil = new JRadioButton("Difícil", false);

        grupo1 = new ButtonGroup();
        grupo1.add(facil);
        grupo1.add(medio);
        grupo1.add(dificil);

        painelRadio.add(facil);
        painelRadio.add(medio);
        painelRadio.add(dificil);

        ButtonHandler handler = new ButtonHandler();
        facil.addActionListener(handler);
        medio.addActionListener(handler);
        dificil.addActionListener(handler);

        addComponente(painelRadio, 3, 1);
    }

    public class ButtonHandler implements ActionListener {
        // TRATA EVENTO DO BOTÃO
        public void actionPerformed(ActionEvent event) {
            if (facil.isSelected()) {
                Questao q = new QuestaoFacil();
                q.exibirAlternativas();
            } else if (medio.isSelected()) {
                Questao q = new QuestaoMedio();
                q.exibirAlternativas();
            } else if (dificil.isSelected()) {
                Questao q = new QuestaoDificil();
                q.exibirAlternativas();
            }
        }
    }

}
