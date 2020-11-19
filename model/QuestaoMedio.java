package model;

import javax.swing.JTextField;

public class QuestaoMedio extends Questao {
    private String[] alternativas;

    public QuestaoMedio() {
        super();

        alternativas = new String[2];
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

    public String getAlternativa3() {
        return alternativas[2];
    }

    public void setAlternativa3(String alternativa3) {
        this.alternativas[2] = alternativa3;
    }

    @Override
    public void criarQuestao(JTextField perguntaTxt, JTextField resposta, JTextField altTxt, JTextField alt2Txt,
            JTextField alt3Txt) {
        super.setPergunta(perguntaTxt.getText());
        super.setResposta(resposta.getText());
        this.setAlternativa(altTxt.getText());
        this.setAlternativa2(alt2Txt.getText());
        this.setAlternativa3(alt3Txt.getText());
        super.setDificuldade(Dificuldade.DIFICIL);
    }

}
