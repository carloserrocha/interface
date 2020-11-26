package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Questao;

public class AppFrame extends JFrame {
    public static final String TITULO = "Mundo dos Numeros";

    private CardLayout layout;
    private JPanel cardsPanel;

    private LoginProfessorPanel painelLoginProfessor;
    private InicialProfessor painelInicial;
    private ListaPerguntas painelListadePerguntas;
    private FormPerguntas painelFormPerguntas;

    public AppFrame() {
        super(TITULO);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layout = new CardLayout();

        cardsPanel = new JPanel();
        cardsPanel.setLayout(layout);
        add(cardsPanel);

        criarCards();
    }

    public void mostrar() {
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrarLoginProfessor() {
        layout.show(cardsPanel, LoginProfessorPanel.class.getName());
    }

    public void mostrarPerguntas() {
        ListaPerguntas.recarregar();
        layout.show(cardsPanel, ListaPerguntas.class.getName());
    }

    public void mostrarFormPerguntas(Questao questao) {
        painelFormPerguntas.setQuestao(questao);
        layout.show(cardsPanel, FormPerguntas.class.getName());
    }

    public void mostrarInicialProfessor() {
        layout.show(cardsPanel, InicialProfessor.class.getName());
    }

    private void criarCards() {
        painelLoginProfessor = new LoginProfessorPanel(this);
        cardsPanel.add(painelLoginProfessor, LoginProfessorPanel.class.getName());

        painelInicial = new InicialProfessor(this);
        cardsPanel.add(painelInicial, InicialProfessor.class.getName());

        painelListadePerguntas = new ListaPerguntas(this);
        cardsPanel.add(painelListadePerguntas, ListaPerguntas.class.getName());

        painelFormPerguntas = new FormPerguntas(this);
        cardsPanel.add(painelFormPerguntas, FormPerguntas.class.getName());

    }
}