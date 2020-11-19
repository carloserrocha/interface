package model;

import javax.swing.JTextField;

public class QuestaoFacil extends Questao {
    private String[] alternativas;

    public QuestaoFacil() {
        super();

        alternativas = new String[1];
    }

    public String getAlternativa() {
        return alternativas[0];
    }

    public void setAlternativa(String alternativa) {
        this.alternativas[0] = alternativa;
    }

    public String getAlternativa2() {
        return alternativas[1];
    }

    public void setAlternativa2(String alternativa2) {
        this.alternativas[1] = alternativa2;
    }

    @Override
    public void criarQuestao(JTextField perguntaTxt, JTextField resposta, JTextField altTxt, JTextField alt2Txt) {
        super.setPergunta(perguntaTxt.getText());
        super.setResposta(resposta.getText());
        this.setAlternativa(altTxt.getText());
        this.setAlternativa2(alt2Txt.getText());
        super.setDificuldade(Dificuldade.FACIL);
    }
}
