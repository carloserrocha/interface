package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FacilQuestaoPanel extends QuestaoPanel {
<<<<<<< Updated upstream
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
=======
	private JTextField[] alternativas;

	public FacilQuestaoPanel() {
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();

	}

	@Override
	public void painelAltenativas() {
		setLayout(layout);
		alternativas = new JTextField[2];
		JLabel rotulo;
		for (int i = 0; i <= 1; i++) {
			rotulo = new JLabel("Alternativa " + (i + 1) + ":");
			addComponente(rotulo, i, 0);
			alternativas[i] = new JTextField(30);
			alternativas[i].setDocument(new LimitChar(100));
			addComponente(alternativas[i], i, 1);
		}

	}

	public void setAlternativa(String alternativa, int i) {
		if (i == 0) {
			this.alternativas[0].setText(alternativa);
		} else if (i == 1) {
			this.alternativas[1].setText(alternativa);
		}
	}

	public String getAlternativa(int i) {
		String e = null;
		switch (i) {
			case 0:
				e = alternativas[0].getText();
				break;
			case 1:
				e = alternativas[1].getText();
				break;
			default:
				return null;
		}
>>>>>>> Stashed changes

}
