package model;

import javax.swing.JTextField;

public abstract class Questao {
    private int id;
    private String pergunta;
    private String resposta;
    private Dificuldade dificuldade;

    public Questao() {

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

    public String getDificuldade() {
        return this.dificuldade.getDescricao();
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public void criarQuestao(JTextField perguntaTxt, JTextField resposta, JTextField altTxt, JTextField alt2Txt) {
        criarQuestao(perguntaTxt, resposta, altTxt, alt2Txt, null, null);
    }

    public void criarQuestao(JTextField perguntaTxt, JTextField resposta, JTextField altTxt, JTextField alt2Txt,
            JTextField alt3Txt) {
        criarQuestao(perguntaTxt, resposta, altTxt, alt2Txt, alt3Txt, null);
    }

    public abstract void criarQuestao(JTextField perguntaTxt, JTextField resposta, JTextField altTxt,
            JTextField alt2Txt, JTextField alt3Txt, JTextField alt4Txt);

    @Override
    public String toString() {
        return String.format("%d: %s", id, pergunta);
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
}// fim da classe Questão
