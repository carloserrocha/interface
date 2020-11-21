package model;

public class QuestaoDificil extends Questao {

    private String[] alternativas;

    public QuestaoDificil() {
        super();

        alternativas = new String[3];
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

    public String getAlternativa4() {
        return alternativas[3];
    }

    public void setAlternativa4(String alternativa4) {
        this.alternativas[3] = alternativa4;
    }

    

}
