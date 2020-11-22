package model;

public abstract class Questao {
    private int id;
    private String pergunta;
    private String resposta;
    private String dica;
    private Dificuldade dificuldade;
    private String[] alternativas;

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

    public void setAlternativa(String alternativa, int i) {
        if (i == 0) {
            this.alternativas[0] = alternativa;
        } else if (i == 1) {
            this.alternativas[1] = alternativa;
        } else if (i == 2) {
            this.alternativas[2] = alternativa;
        } else {
            this.alternativas[3] = alternativa;
        }
    }

    public String getAlternativas(int i) {
        String e = null;
        switch (i) {
            case 0:
                e = alternativas[i];
            case 1:
                e = alternativas[i];
            case 2:
                e = alternativas[i];
            case 3:
                e = alternativas[i];
            case 4:
                e = alternativas[i];
            default:
                e = null;
        }

        return e;
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
