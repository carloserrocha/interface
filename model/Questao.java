package model;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.plaf.InsetsUIResource;

import view.AppFrame;

public abstract class Questao {
    private AppFrame frame;

    private static final Insets FIELD_INSETS = new InsetsUIResource(5, 10, 0, 0);
    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private int id;
    private String pergunta;
    private String resposta;

    public Questao(AppFrame appFrame) {
        this.frame = appFrame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public abstract void exibirAlternativas();

    @Override
    public String toString() {
        return String.format("%d: %s, %s", id, pergunta);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Questao outraQuestao = (Questao) obj;
        return id == outraQuestao.id;
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

}// fim da classe Questão
