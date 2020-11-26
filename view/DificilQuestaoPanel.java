package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class DificilQuestaoPanel extends QuestaoPanel {
	private JTextField[] alternativa;

	public DificilQuestaoPanel() {
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		setLayout(layout);
		setBackground(new Color(0, 159, 136));
	}

	@Override
	public void painelAltenativas() {
		alternativa = new JTextField[4];
		JLabel rotulo;
		for (int i = 0; i <= 3; i++) {
			rotulo = new JLabel("Alternativa " + (i + 1) + ":");
			rotulo.setForeground(Color.WHITE);
			addComponente(rotulo, i, 0);
			alternativa[i] = new JTextField(45);
			alternativa[i].setDocument(new LimitChar(100));
			addComponente(alternativa[i], i, 1);

		}

	}

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
			case 4:
				e = alternativa[i].getText();
				break;
			default:
				System.out.printf("[ERRO] Painel Difícil");
				break;
		}

		return e;
	}
}
