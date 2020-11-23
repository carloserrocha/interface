package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class QuestaoPanel extends JPanel {
	protected GridBagLayout layout;
	protected GridBagConstraints constraints;

	public QuestaoPanel() {

		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
	}

	public abstract void painelAltenativas();

	public void addComponente(JComponent rotulo, int linha, int coluna) {

		addComponente(rotulo, linha, coluna, 1, 1);
	}

	public void addComponente(JComponent componente, int linha, int coluna, int largura, int altura) {
		constraints.gridx = coluna;
		constraints.gridy = linha;
		constraints.gridwidth = largura;
		constraints.gridheight = altura;

		constraints.fill = GridBagConstraints.VERTICAL;

		layout.setConstraints(componente, constraints);

		add(componente);

	}

	public abstract String getAlternativa(int i);

	public abstract void setAlternativa(String s, int i);

}
