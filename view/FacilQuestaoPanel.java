package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FacilQuestaoPanel extends QuestaoPanel {
    private JTextField[] alternativa;
    private FormPerguntas formulario;
    
    public FacilQuestaoPanel(FormPerguntas formperguntas) {
	layout = new GridBagLayout();
	constraints = new GridBagConstraints();
	this.formulario = formperguntas;
	
	
    }
    @Override
    public void painelAltenativas() {
	alternativa = new JTextField[2];	
	JLabel rotulo;
	for(int i = 0; i <= 1; i++) {
	  rotulo =  new JLabel("Alternativa" + (i + 1));
	  addComponente(rotulo, i, 0);
	  alternativa[i] = new JTextField(30);
	  alternativa[i].setDocument(new LimitChar(100));
	  addComponente(alternativa[i], i, 1);
	}

    }

}
