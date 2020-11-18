package model;

import javax.swing.JLabel;
import javax.swing.JTextField;

import view.LimitChar;

public class QuestaoMedio extends Questao {
    private String alternativa;
    private String alternativa2;
    private String alternativa3;
    private Dificuldade dificuldade;

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public String getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(String alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public String getAlternativa3() {
        return alternativa3;
    }

    public String getDificuldade() {
        return this.dificuldade.getDescricao();
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    @Override
    public void exibirAlternativas(){
        JLabel rotulo;
        rotulo = new JLabel("Primeira Alternativa");
        addComponente(rotulo, 4, 0);
        altTxt = new JTextField(30);
        altTxt.setDocument(new LimitChar(100));
        addComponente(altTxt, 4, 1);

        rotulo = new JLabel("Segunda Alternativa");
        addComponente(rotulo, 5, 0);
        alt2Txt = new JTextField(30);
        alt2Txt.setDocument(new LimitChar(100));
        addComponente(alt2Txt, 5, 1);

        rotulo = new JLabel("Terceira Alternativa");
        addComponente(rotulo, 6, 0);
        alt3Txt = new JTextField(30);
        alt3Txt.setDocument(new LimitChar(100));
        addComponente(alt3Txt, 6, 1);

    }

}
