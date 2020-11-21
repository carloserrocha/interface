package model;

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

    
}
