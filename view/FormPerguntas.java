package view;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.InsetsUIResource;

import model.Dificuldade;
import model.MnDB;
import model.Questao;

public class FormPerguntas extends JPanel {
	private AppFrame frame;

	private static final Insets FIELD_INSETS = new InsetsUIResource(5, 10, 0, 0);
	private GridBagLayout layout;
	private GridBagConstraints constraints;

	private JTextField idTxt;
	private JTextField perguntaTxt;
	private JTextField dicaTxt;
	private JTextField resposta;
	private JButton salvarBtn;
	private JButton cancelarBtn;

	private JRadioButton facil;
	private JRadioButton medio;
	private JRadioButton dificil;

	private ButtonGroup grupo1;

	private Questao questao;
	private QuestaoPanel panelAltern;
	private QuestaoPanel facilPanel;
	private QuestaoPanel medioPanel;
	private QuestaoPanel dificilPanel;

	private CardLayout layoutAltern;
	private JPanel cardsPanelAltern;
	// private int f = 0;

	public FormPerguntas(AppFrame appFrame) {
		this.frame = appFrame;
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();

		questao = null;

		setLayout(layout);
		// Zerar campos do formulario.
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentShown(ComponentEvent e) {
				if (questao == null) {
					idTxt.setText("");
					perguntaTxt.setText("");
					dicaTxt.setText("");
					resposta.setText("");

					if (facil.isSelected() == true) {
						facil.setSelected(true);
						panelAltern.setAlternativa("", 0);
						panelAltern.setAlternativa("", 1);
					} else if (medio.isSelected() == true) {
						medio.setSelected(true);
						panelAltern.setAlternativa("", 0);
						panelAltern.setAlternativa("", 1);
						panelAltern.setAlternativa("", 2);
					} else {
						dificil.setSelected(true);
						panelAltern.setAlternativa("", 0);
						panelAltern.setAlternativa("", 1);
						panelAltern.setAlternativa("", 2);
						panelAltern.setAlternativa("", 3);
					}

				} else {
					idTxt.setText(Integer.toString(questao.getId()));
					perguntaTxt.setText(questao.getPergunta());
					dicaTxt.setText(questao.getDica());
					resposta.setText(questao.getResposta());
				}

			}
		});

		criarForm();
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	private void criarForm() {
		JLabel rotulo;
		rotulo = new JLabel("Id");
		addComponente(rotulo, 0, 0);
		idTxt = new JTextField(30);
		idTxt.setEditable(false);
		addComponente(idTxt, 0, 1);

		rotulo = new JLabel("Pergunta");
		addComponente(rotulo, 1, 0);
		perguntaTxt = new JTextField(30);
		perguntaTxt.setDocument(new LimitChar(100));
		addComponente(perguntaTxt, 1, 1);

		rotulo = new JLabel("Dica");
		addComponente(rotulo, 2, 0);
		dicaTxt = new JTextField(30);
		dicaTxt.setDocument(new LimitChar(100));
		addComponente(dicaTxt, 2, 1);

		rotulo = new JLabel("Resposta");
		addComponente(rotulo, 3, 0);
		resposta = new JTextField(30);
		resposta.setDocument(new LimitChar(100));
		addComponente(resposta, 3, 1);

		rotulo = new JLabel("Selecione a dificuldade da questão:");
		addComponente(rotulo, 4, 0);
		colocarRadio();
		layoutAltern = new CardLayout();
		cardsPanelAltern = new JPanel();
		cardsPanelAltern.setLayout(layoutAltern);
		addComponente(cardsPanelAltern, 5, 0, 4, 4);
		criarCards();

		facil.setSelected(true);
		criarBtn();
	}

	public void mostrarAlternativas() {
		panelAltern.painelAltenativas();
		System.out.printf("%s linha 151 |", panelAltern.getName());
	}

	public void criarCards() {
		facilPanel = new FacilQuestaoPanel();
		medioPanel = new MediaQuestaoPanel();
		dificilPanel = new DificilQuestaoPanel();
		cardsPanelAltern.add(facilPanel, FacilQuestaoPanel.class.getName());
		cardsPanelAltern.add(medioPanel, MediaQuestaoPanel.class.getName());
		cardsPanelAltern.add(dificilPanel, DificilQuestaoPanel.class.getName());
	}

	public void alterCards() {
		if (facil.isSelected() == true) {
			panelAltern = facilPanel;
		} else if (medio.isSelected() == true) {
			panelAltern = medioPanel;
		} else if (dificil.isSelected() == true) {
			panelAltern = dificilPanel;
		}
	}

	private void criarBtn() {
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		saveBtn();
		btnPanel.add(salvarBtn);
		cancelBtn();
		btnPanel.add(cancelarBtn);
		criarCards();
		addComponente(btnPanel, 10, 1, 2, 1);

	}

	private void saveBtn() {
		salvarBtn = new JButton("Salvar");
		salvarBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				questao = new Questao();

				if (facil.isSelected()) {
					criarQuestaoFacil();
				} else if (medio.isSelected()) {
					criarQuestaoMedia();
				} else if (dificil.isSelected()) {
					criarQuestaoDificil();
				}

				if (idTxt.getText().isBlank()) {
					MnDB.inserir(questao);
					JOptionPane.showMessageDialog(FormPerguntas.this, "Questão criada com sucesso!", AppFrame.TITULO,
							JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
					criarCards();

				} else if (!idTxt.getText().isBlank()) {
					questao.setId(Integer.parseInt(idTxt.getText()));
					MnDB.atualizar(questao);// atualiza a questão no "Banco de dados"
					JOptionPane.showMessageDialog(FormPerguntas.this, "Questão Editada com sucesso!", AppFrame.TITULO,
							JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário

				}
				frame.mostrarPerguntas();// volta para a lista de perguntas

			}
		});

	}

	public void criarQuestaoFacil() {
		System.out.print("criarQuestaoFacil() \n");
		System.out.printf("Valor campo pergunta: %s%n", perguntaTxt.getText());
		System.out.printf("Valor campo resposta: %s%n", resposta.getText());
		System.out.printf("Valor campo dica: %s%n", dicaTxt.getText());
		System.out.printf("Valor campo Alternativa1: %s%n", panelAltern.getAlternativa(0));
		System.out.printf("Valor campo Alternativa2: %s%n", panelAltern.getAlternativa(1));

		if ((perguntaTxt.getText().length() > 0) && (resposta.getText().length() > 0)
				&& (dicaTxt.getText().length() > 0) && (panelAltern.getAlternativa(0).length() > 0)
				&& (panelAltern.getAlternativa(1).length() > 0)) {
			questao.setPergunta(perguntaTxt.getText());
			questao.setResposta(resposta.getText());
			questao.setDica(dicaTxt.getText());
			questao.setAlternativa(panelAltern.getAlternativa(0));
			questao.setAlternativa2(panelAltern.getAlternativa(1));
			questao.setDificuldade(Dificuldade.FACIL);
			System.out.printf("Valor campo Dificuldade: %s%n", questao.getDescricao());
		} else {
			JOptionPane.showMessageDialog(FormPerguntas.this,
					"Voce esqueceu de preencher um campo, verifique e tente novamente!", AppFrame.TITULO,
					JOptionPane.ERROR_MESSAGE);// mensagem de erro par alertar o usuário que tem campos vazios
		}
	}

	public void criarQuestaoMedia() {
		System.out.print("criarQuestaoMedia() \n");
		System.out.printf("Valor campo pergunta: %s%n", perguntaTxt.getText());
		System.out.printf("Valor campo resposta: %s%n", resposta.getText());
		System.out.printf("Valor campo dica: %s%n", dicaTxt.getText());
		System.out.printf("Valor campo Alternativa1: %s%n", panelAltern.getAlternativa(0));
		System.out.printf("Valor campo Alternativa2: %s%n", panelAltern.getAlternativa(1));
		System.out.printf("Valor campo Alternativa3: %s%n", panelAltern.getAlternativa(2));
		if ((perguntaTxt.getText().length() > 0) && (resposta.getText().length() > 0)
				&& (dicaTxt.getText().length() > 0) && (panelAltern.getAlternativa(0).length() > 0)
				&& (panelAltern.getAlternativa(1).length() > 0) && (panelAltern.getAlternativa(2).length() > 0)) {
			questao.setPergunta(perguntaTxt.getText());
			questao.setResposta(resposta.getText());
			questao.setDica(dicaTxt.getText());
			questao.setAlternativa(panelAltern.getAlternativa(0));
			questao.setAlternativa2(panelAltern.getAlternativa(1));
			questao.setAlternativa3(panelAltern.getAlternativa(2));
			questao.setDificuldade(Dificuldade.MEDIO);
			System.out.printf("Valor campo Dificuldade: %s%n", questao.getDescricao());
		} else {
			JOptionPane.showMessageDialog(FormPerguntas.this,
					"Voce esqueceu de preencher um campo, verifique e tente novamente!", AppFrame.TITULO,
					JOptionPane.ERROR_MESSAGE);// mensagem de erro par alertar o usuário que tem campos vazios
		}
	}

	public void criarQuestaoDificil() {
		System.out.print("criarQuestaoDificil() \n");
		System.out.printf("Valor campo pergunta: %s%n", perguntaTxt.getText());
		System.out.printf("Valor campo resposta: %s%n", resposta.getText());
		System.out.printf("Valor campo dica: %s%n", dicaTxt.getText());
		System.out.printf("Valor campo Alternativa1: %s%n", panelAltern.getAlternativa(0));
		System.out.printf("Valor campo Alternativa2: %s%n", panelAltern.getAlternativa(1));
		System.out.printf("Valor campo Alternativa3: %s%n", panelAltern.getAlternativa(2));
		System.out.printf("Valor campo Alternativa4: %s%n", panelAltern.getAlternativa(3));
		if ((perguntaTxt.getText().length() > 0) && (resposta.getText().length() > 0)
				&& (dicaTxt.getText().length() > 0) && (panelAltern.getAlternativa(0).length() > 0)
				&& (panelAltern.getAlternativa(1).length() > 0) && (panelAltern.getAlternativa(2).length() > 0)
				&& (panelAltern.getAlternativa(3).length() > 0)) {
			questao.setPergunta(perguntaTxt.getText());
			questao.setResposta(resposta.getText());
			questao.setDica(dicaTxt.getText());
			questao.setAlternativa(panelAltern.getAlternativa(0));
			questao.setAlternativa2(panelAltern.getAlternativa(1));
			questao.setAlternativa3(panelAltern.getAlternativa(2));
			questao.setAlternativa4(panelAltern.getAlternativa(3));
			questao.setDificuldade(Dificuldade.DIFICIL);
			System.out.printf("Valor campo Dificuldade: %s%n", questao.getDescricao());
		} else {

			JOptionPane.showMessageDialog(FormPerguntas.this,
					"Voce esqueceu de preencher um campo, verifique e tente novamente!", AppFrame.TITULO,
					JOptionPane.ERROR_MESSAGE);// mensagem de erro par alertar o usuário que tem campos vazios
		}
	}

	private void cancelBtn() {
		cancelarBtn = new JButton("Cancelar");
		cancelarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.mostrarPerguntas();// volta para a lista de Perguntas
			}
		});
	}

	private void addComponente(JComponent rotulo, int linha, int coluna) {

		addComponente(rotulo, linha, coluna, 1, 1);
	}

	private void addComponente(JComponent componente, int linha, int coluna, int largura, int altura) {
		constraints.gridx = coluna;
		constraints.gridy = linha;
		constraints.gridwidth = largura;
		constraints.gridheight = altura;

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = FIELD_INSETS;

		layout.setConstraints(componente, constraints);

		add(componente);

	}

	private void colocarRadio() {
		JPanel painelRadio = new JPanel();

		facil = new JRadioButton("Fácil", false);
		facil.setActionCommand("Fácil");
		facil.addItemListener(new ButtonHandler());
		medio = new JRadioButton("Médio", false);
		medio.setActionCommand("Médio");
		medio.addItemListener(new ButtonHandler());
		dificil = new JRadioButton("Difícil", false);
		dificil.setActionCommand("Difícil");
		dificil.addItemListener(new ButtonHandler());

		grupo1 = new ButtonGroup();
		grupo1.add(facil);
		grupo1.add(medio);
		grupo1.add(dificil);

		painelRadio.add(facil);
		painelRadio.add(medio);
		painelRadio.add(dificil);

		addComponente(painelRadio, 4, 1);

	}

	private class ButtonHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (facil.isSelected() == true) {
				// for (int i = f; i < 1; i++) {
				// alterCards();
				// mostrarAlternativas();
				// }
				alterCards();
				mostrarAlternativas();
				layoutAltern.show(cardsPanelAltern, FacilQuestaoPanel.class.getName());

			} else if (medio.isSelected() == true) {
				// for (int i = f; i < 1; i++) {
				// alterCards();
				// mostrarAlternativas();
				// }
				alterCards();
				mostrarAlternativas();
				layoutAltern.show(cardsPanelAltern, MediaQuestaoPanel.class.getName());
			} else if (dificil.isSelected() == true) {
				// for (int i = f; i < 1; i++) {
				// alterCards();
				// mostrarAlternativas();
				// }
				alterCards();
				mostrarAlternativas();
				layoutAltern.show(cardsPanelAltern, DificilQuestaoPanel.class.getName());
			}
		}
	}

}