package model;

import view.FormPerguntas;

public class Questao {
    private int id;
    private String pergunta;
    private String resposta;
    private String dica;
    private Dificuldade dificuldade;
    private String alternativa;
    private String alternativa2;
    private String alternativa3;
    private String alternativa4;
    private FormPerguntas frame;

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

    public void setDica(String dica) {
	this.dica = dica;
    }

    public String getDica() {
	return dica;
    }

    public void setAlternativa(String alternativa) {
	this.alternativa = alternativa;
    }

    public void setAlternativa2(String alternativa2) {
	this.alternativa2 = alternativa2;
    }

    public void setAlternativa3(String alternativa3) {
	this.alternativa3 = alternativa3;
    }

    public void setAlternativa4(String alternativa4) {
	this.alternativa4 = alternativa4;
    }

    public String getAlternativa() {
	return alternativa;
    }

    public String getAlternativa2() {
	return alternativa2;
    }

    public String getAlternativa3() {
	return alternativa3;
    }

    public String getAlternativa4() {
	return alternativa4;
    }

    public String getDescricao() {
	return this.dificuldade.getDescricao();
    }

    public Dificuldade getDificuldade() {
	return this.dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
	this.dificuldade = dificuldade;
    }

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
