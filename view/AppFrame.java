package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppFrame extends JFrame {
    public static final String TITULO = "Mundo dos Numeros";

    private CardLayout layout;
    private JPanel cardsPanel;

    private InicialProfessor painelInicial;
    private ListaPerguntas painelListadeTarefas;
    private FormPerguntas painelFormTarefas;

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

    public void mostrarFormPerguntas() {
        layout.show(cardsPanel, FormPerguntas.class.getName());
    }

    public void voltar() {
        layout.show(cardsPanel, InicialProfessor.class.getName());
    }

    private void criarCards() {
        painelInicial = new InicialProfessor(this);
        cardsPanel.add(painelInicial, InicialProfessor.class.getName());

        painelListadeTarefas = new ListaPerguntas(this);
        cardsPanel.add(painelListadeTarefas, ListaPerguntas.class.getName());

        painelFormTarefas = new FormPerguntas(this);
        cardsPanel.add(painelFormTarefas, FormPerguntas.class.getName());
    }
}