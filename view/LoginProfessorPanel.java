package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.InsetsUIResource;

import model.LoginProfessor;

public class LoginProfessorPanel extends JPanel {
    private static final Insets FIELD_INSETS = new InsetsUIResource(5, 10, 0, 0);
    private AppFrame frame;
    private JButton login;
    private JButton sair;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JTextField loginField;
    private JPasswordField password;
    private LoginProfessor c;
    private final char[] VERIFICACAO = { '1', '2', '3', '4', '5' };

    public LoginProfessorPanel(AppFrame appFrame) {
        this.frame = appFrame;
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        setLayout(layout);

        criarForm();
        criarBotoes();
    }

    public void criarForm() {
        JLabel rotulo;
        rotulo = new JLabel("Usuário");
        addComponente(rotulo, 0, 0);
        loginField = new JTextField(30);
        addComponente(loginField, 0, 1);

        rotulo = new JLabel("Senha");
        addComponente(rotulo, 1, 0);
        password = new JPasswordField(30);
        addComponente(password, 1, 1);
    }

    public void criarBotoes() {
        login = new JButton("Login");// Criar Pergunta
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarInicialProfessor();
            }
        });
        addComponente(login, 2, 1);

        sair = new JButton("Sair");
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarLoginProfessor();
            }
        });

        addComponente(sair, 2, 0);

    }

    public void addComponente(JComponent rotulo, int linha, int coluna) {
        addComponente(rotulo, linha, coluna, 1, 1);
    }

    public void addComponente(JComponent componente, int linha, int coluna, int largura, int altura) {
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
