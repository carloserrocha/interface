package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Questao;

public class AppFrame extends JFrame {
    public static final String TITULO = "Mundo dos Numeros";

    private CardLayout layout;
    private JPanel cardsPanel;

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
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrarPerguntas() {
        ListaPerguntas.recarregar();

        layout.show(cardsPanel, ListaPerguntas.class.getName());
    }

    public void mostrarFormPerguntas(Questao questao) {
        painelFormPerguntas.setQuestao(questao);
        layout.show(cardsPanel, FormPerguntas.class.getName());
    }

    public void voltar() {
        layout.show(cardsPanel, InicialProfessor.class.getName());
    }

    private void criarCards() {
        painelInicial = new InicialProfessor(this);
        cardsPanel.add(painelInicial, InicialProfessor.class.getName());

        painelListadePerguntas = new ListaPerguntas(this);
        cardsPanel.add(painelListadePerguntas, ListaPerguntas.class.getName());

        painelFormPerguntas = new FormPerguntas(this);
        cardsPanel.add(painelFormPerguntas, FormPerguntas.class.getName());
    }
}