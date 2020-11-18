package model;

import javax.swing.JLabel;
import javax.swing.JTextField;

import view.LimitChar;

public class QuestaoFacil extends Questao {
    private String alternativa;
    private String alternativa2;
    private Dificuldade dificuldade;

    public QuestaoFacil(){
        
    }

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

    public String getDificuldade() {
        return this.dificuldade.getDescricao();
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public void criarQuestao(){
        super.Pergunta(perguntaTxt.getText();
        super.setResposta(resposta.getText());
        this.setAlternativa(altTxt.getText());
        this.setAlternativa2(alt2Txt.getText());
        this.setDificuldade(Dificuldade.FACIL);
        
    }

    @Override
    public void exibirAlternativas(){
        this.frame = appFrame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        questao = null;

        setLayout(layout);
        criarForm();

    }
    private void criarForm(){
        JLabel rotulo;
        rotulo = new JLabel("Primeira Alternativa");
        addComponente(rotulo, 4, 0);
        alternativa = new JTextField(30);
        alternativa.setDocument(new LimitChar(100));
        addComponente(alternativa2, 4, 1);

        rotulo = new JLabel("Segunda Alternativa");
        addComponente(rotulo, 5, 0);
        alternativa2 = new JTextField(30);
        alternativa2.setDocument(new LimitChar(100));
        addComponente(alternativa2, 5, 1);}
}
