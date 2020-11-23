package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class DificilQuestaoPanel extends QuestaoPanel {
    private JTextField[] alternativa;
    private FormPerguntas formulario;
  

    public DificilQuestaoPanel(FormPerguntas formperguntas) {
	layout = new GridBagLayout();
	constraints = new GridBagConstraints();
	this.formulario = formperguntas;
	
	
    }

    @Override
    public void painelAltenativas() {
	setLayout(layout);
	alternativa = new JTextField[4];
	JLabel rotulo;
	for (int i = 0; i <= 3; i++) {
	    rotulo = new JLabel("Alternativa " + (i + 1) + ": ");
	    addComponente(rotulo, i, 0);
	    alternativa[i] = new JTextField(30);
	    alternativa[i].setDocument(new LimitChar(100));
	    addComponente(alternativa[i], i, 1);

	}

    }


<<<<<<< Updated upstream
=======
	public void setAlternativa(String alternativa, int i) {
		if (i == 0) {
			this.alternativa[0].setText(alternativa);
		} else if (i == 1) {
			this.alternativa[1].setText(alternativa);
		} else if (i == 2) {
			this.alternativa[2].setText(alternativa);
		} else {
			this.alternativa[3].setText(alternativa);
		}
	}

	public String getAlternativa(int i) {
		String e = null;
		switch (i) {
			case 0:
				e = alternativa[0].getText();
				break;
			case 1:
				e = alternativa[1].getText();
				break;
			case 2:
				e = alternativa[2].getText();
				break;
			case 3:
				e = alternativa[3].getText();
				break;
			default:
				return null;
		}

		return e;
	}
>>>>>>> Stashed changes
}
