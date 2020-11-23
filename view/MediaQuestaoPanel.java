package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MediaQuestaoPanel extends QuestaoPanel {
	private JTextField[] alternativa;

	public MediaQuestaoPanel() {
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();

	}

	@Override
	public void painelAltenativas() {
		setLayout(layout);
		alternativa = new JTextField[3];
		JLabel rotulo;
		for (int i = 0; i <= 2; i++) {
			rotulo = new JLabel("Alternativa " + (i + 1) + ":");
			addComponente(rotulo, i, 0);
			alternativa[i] = new JTextField(30);
			alternativa[i].setDocument(new LimitChar(100));
			addComponente(alternativa[i], i, 1);
		}

	}

	public void setAlternativa(String alternativa, int i) {
		if (i == 0) {
			this.alternativa[0].setText(alternativa);
		} else if (i == 1) {
			this.alternativa[1].setText(alternativa);
		} else {
			this.alternativa[2].setText(alternativa);
		}
	}

	public String getAlternativa(int i) {
		String e = null;
		switch (i) {
			case 0:
				e = alternativa[i].getText();
				break;
			case 1:
				e = alternativa[i].getText();
				break;
			case 2:
				e = alternativa[i].getText();
				break;
			case 3:
				e = alternativa[i].getText();
				break;
			default:
				return null;
		}

		return e;
	}

}
