package view;

import java.awt.CardLayout;
import java.awt.Color;
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
	private QuestaoPanel ePanel;
	private QuestaoPanel mPanel;
	private QuestaoPanel hPanel;

	private CardLayout layoutAltern;
	private JPanel cardsPanelAltern;

	public FormPerguntas(AppFrame appFrame) {
		this.frame = appFrame;
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		setBackground(new Color(0, 159, 136));
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

					if (facil.isSelected()) {
						facil.setSelected(true);
						panelAltern.setAlternativa("", 0);
						panelAltern.setAlternativa("", 1);
					} else if (medio.isSelected()) {
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

					if (questao.getDificuldade() == Dificuldade.FACIL) {
						facil.setSelected(true);
						panelAltern.setAlternativa(questao.getAlternativa(), 0);
						panelAltern.setAlternativa(questao.getAlternativa2(), 1);

					} else if (questao.getDificuldade() == Dificuldade.MEDIO) {
						medio.setSelected(true);
						panelAltern.setAlternativa(questao.getAlternativa(), 0);
						panelAltern.setAlternativa(questao.getAlternativa2(), 1);
						panelAltern.setAlternativa(questao.getAlternativa3(), 2);
					} else if (questao.getDificuldade() == Dificuldade.DIFICIL) {
						dificil.setSelected(true);
						panelAltern.setAlternativa(questao.getAlternativa(), 0);
						panelAltern.setAlternativa(questao.getAlternativa2(), 1);
						panelAltern.setAlternativa(questao.getAlternativa3(), 2);
						panelAltern.setAlternativa(questao.getAlternativa4(), 3);
					}
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
		rotulo.setForeground(Color.WHITE);
		addComponente(rotulo, 0, 0);
		idTxt = new JTextField(30);
		idTxt.setEditable(false);
		addComponente(idTxt, 0, 1);

		rotulo = new JLabel("Pergunta");
		rotulo.setForeground(Color.WHITE);
		addComponente(rotulo, 1, 0);
		perguntaTxt = new JTextField(30);
		perguntaTxt.setDocument(new LimitChar(100));
		addComponente(perguntaTxt, 1, 1);

		rotulo = new JLabel("Dica");
		rotulo.setForeground(Color.WHITE);
		addComponente(rotulo, 2, 0);
		dicaTxt = new JTextField(30);
		dicaTxt.setDocument(new LimitChar(100));
		addComponente(dicaTxt, 2, 1);

		rotulo = new JLabel("Resposta");
		rotulo.setForeground(Color.WHITE);
		addComponente(rotulo, 3, 0);
		resposta = new JTextField(30);
		resposta.setDocument(new LimitChar(100));
		addComponente(resposta, 3, 1);

		rotulo = new JLabel("Selecione a dificuldade da questão:");
		rotulo.setForeground(Color.WHITE);
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

	// Parte do Polimorfismo
	public void criarAlternativas() {
		ePanel.painelAltenativas();
		mPanel.painelAltenativas();
		hPanel.painelAltenativas();
	}

	// Parte do Polimorfismo
	public void criarCards() {
		ePanel = new FacilQuestaoPanel();
		mPanel = new MediaQuestaoPanel();
		hPanel = new DificilQuestaoPanel();
		criarAlternativas();
		cardsPanelAltern.add(ePanel, FacilQuestaoPanel.class.getName()); // Adiciona o objeto da super classe dentro
																			// do cardsPanelAltern
		cardsPanelAltern.add(mPanel, MediaQuestaoPanel.class.getName()); // Adiciona o objeto da super classe dentro
																			// do cardsPanelAltern
		cardsPanelAltern.add(hPanel, DificilQuestaoPanel.class.getName()); // Adiciona o objeto da super classe
																			// dentro do cardsPanelAltern
	}

	// Parte do Polimorfismo
	public void alterCards() {
		if (facil.isSelected() == true) {
			panelAltern = ePanel; // Troca o objeto dentro do panelAltern
		} else if (medio.isSelected() == true) {
			panelAltern = mPanel; // Troca o objeto dentro do panelAltern
		} else if (dificil.isSelected() == true) {
			panelAltern = hPanel; // Troca o objeto dentro do panelAltern
		}
	}

	// Metodo que cria os botões
	private void criarBtn() {
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		saveBtn(); // Chama o metodo que cria o botão salvar
		btnPanel.add(salvarBtn);
		cancelBtn();// Chama o metodo que cria o botão cancelar
		btnPanel.add(cancelarBtn);
		criarCards(); // Chama o metoo que cria os Cards
		btnPanel.setBackground(new Color(0, 159, 136));
		addComponente(btnPanel, 10, 1, 2, 1);

	}

	// Metodo que cria o botão salvar
	private void saveBtn() {
		salvarBtn = new JButton("Salvar");
		salvarBtn.addActionListener(new ActionListener() {
			// metodo que "Escuta" a ação no botão salvar
			@Override
			public void actionPerformed(ActionEvent e) {
				questao = new Questao();

				if (facil.isSelected()) {
					criarQuestaoFacil(); // cria a questão fácil
				} else if (medio.isSelected()) {
					criarQuestaoMedia(); // cria a questão média
				} else if (dificil.isSelected()) {
					criarQuestaoDificil(); // cria a questão difícil
				}
				if (idTxt.getText().isBlank()) {
					if ((facil.isSelected()) && (perguntaTxt.getText().length() > 0)
							&& (resposta.getText().length() > 0) && (dicaTxt.getText().length() > 0)
							&& (panelAltern.getAlternativa(0).length() > 0)
							&& (panelAltern.getAlternativa(1).length() > 0)) // Verificação dos campos antes de salvar a
																				// questão fácil
					{
						MnDB.inserir(questao);
						JOptionPane.showMessageDialog(FormPerguntas.this, "Questão criada com sucesso!",
								AppFrame.TITULO, JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
					} else if ((medio.isSelected()) && (perguntaTxt.getText().length() > 0)
							&& (resposta.getText().length() > 0) && (dicaTxt.getText().length() > 0)
							&& (panelAltern.getAlternativa(0).length() > 0)
							&& (panelAltern.getAlternativa(1).length() > 0)
							&& (panelAltern.getAlternativa(2).length() > 0))// Verificação dos campos antes de salvar a
																			// questão média
					{
						MnDB.inserir(questao);
						JOptionPane.showMessageDialog(FormPerguntas.this, "Questão criada com sucesso!",
								AppFrame.TITULO, JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
					} else if ((dificil.isSelected()) && (perguntaTxt.getText().length() > 0)
							&& (resposta.getText().length() > 0) && (dicaTxt.getText().length() > 0)
							&& (panelAltern.getAlternativa(0).length() > 0)
							&& (panelAltern.getAlternativa(1).length() > 0)
							&& (panelAltern.getAlternativa(2).length() > 0)
							&& (panelAltern.getAlternativa(3).length() > 0))// Verificação dos campos antes de salvar a
																			// questão difícil
					{
						MnDB.inserir(questao);
						JOptionPane.showMessageDialog(FormPerguntas.this, "Questão criada com sucesso!",
								AppFrame.TITULO, JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
					}

					criarCards();
				} else if (!idTxt.getText().isBlank()) {
					if ((facil.isSelected()) && (perguntaTxt.getText().length() > 0)
							&& (resposta.getText().length() > 0) && (dicaTxt.getText().length() > 0)
							&& (panelAltern.getAlternativa(0).length() > 0)
							&& (panelAltern.getAlternativa(1).length() > 0)) // Verificação dos campos antes de editar a
																				// questão fácil
					{
						questao.setId(Integer.parseInt(idTxt.getText()));
						MnDB.atualizar(questao);// atualiza a questão no "Banco de dados"
						JOptionPane.showMessageDialog(FormPerguntas.this, "Questão Editada com sucesso!",
								AppFrame.TITULO, JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
					} else if ((medio.isSelected()) && (perguntaTxt.getText().length() > 0)
							&& (resposta.getText().length() > 0) && (dicaTxt.getText().length() > 0)
							&& (panelAltern.getAlternativa(0).length() > 0)
							&& (panelAltern.getAlternativa(1).length() > 0)
							&& (panelAltern.getAlternativa(2).length() > 0)) // Verificação dos campos antes de editar a
																				// questão média
					{
						questao.setId(Integer.parseInt(idTxt.getText()));
						MnDB.atualizar(questao);// atualiza a questão no "Banco de dados"
						JOptionPane.showMessageDialog(FormPerguntas.this, "Questão Editada com sucesso!",
								AppFrame.TITULO, JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
					} else if ((dificil.isSelected()) && (perguntaTxt.getText().length() > 0)
							&& (resposta.getText().length() > 0) && (dicaTxt.getText().length() > 0)
							&& (panelAltern.getAlternativa(0).length() > 0)
							&& (panelAltern.getAlternativa(1).length() > 0)
							&& (panelAltern.getAlternativa(2).length() > 0)
							&& (panelAltern.getAlternativa(3).length() > 0)) // Verificação dos campos antes de editar a
																				// questão difícil
					{
						questao.setId(Integer.parseInt(idTxt.getText()));
						MnDB.atualizar(questao);// atualiza a questão no "Banco de dados"
						JOptionPane.showMessageDialog(FormPerguntas.this, "Questão Editada com sucesso!",
								AppFrame.TITULO, JOptionPane.INFORMATION_MESSAGE);// Mensagem de confirmação ao usuário
					}

				}
				frame.mostrarPerguntas();// volta para a lista de perguntas
			}
		});

	}

	// Metodo que cria a questão Fácil
	public void criarQuestaoFacil() {
		if ((perguntaTxt.getText().length() > 0) && (resposta.getText().length() > 0)
				&& (dicaTxt.getText().length() > 0) && (panelAltern.getAlternativa(0).length() > 0)
				&& (panelAltern.getAlternativa(1).length() > 0)) {
			questao.setPergunta(perguntaTxt.getText());
			questao.setResposta(resposta.getText());
			questao.setDica(dicaTxt.getText());
			questao.setAlternativa(panelAltern.getAlternativa(0));
			questao.setAlternativa2(panelAltern.getAlternativa(1));
			questao.setDificuldade(Dificuldade.FACIL);
		} else {
			JOptionPane.showMessageDialog(FormPerguntas.this,
					"Voce esqueceu de preencher um campo, verifique e tente novamente!", AppFrame.TITULO,
					JOptionPane.ERROR_MESSAGE);// mensagem de erro par alertar o usuário que tem campos vazios
		}
	}

	// Metodo que cria a questão Média
	public void criarQuestaoMedia() {
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
		} else {
			JOptionPane.showMessageDialog(FormPerguntas.this,
					"Voce esqueceu de preencher um campo, verifique e tente novamente!", AppFrame.TITULO,
					JOptionPane.ERROR_MESSAGE);// mensagem de erro par alertar o usuário que tem campos vazios
		}
	}

	// Metodo que cria a questão Difícil
	public void criarQuestaoDificil() {
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
		facil.setBackground(new Color(0, 159, 136));
		facil.setForeground(Color.WHITE);
		medio.setBackground(new Color(0, 159, 136));
		medio.setForeground(Color.WHITE);
		dificil.setBackground(new Color(0, 159, 136));
		dificil.setForeground(Color.WHITE);
		grupo1 = new ButtonGroup();
		grupo1.add(facil);
		grupo1.add(medio);
		grupo1.add(dificil);

		painelRadio.add(facil);
		painelRadio.add(medio);
		painelRadio.add(dificil);
		painelRadio.setBackground(new Color(0, 159, 136));
		addComponente(painelRadio, 4, 1);

	}

	private class ButtonHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (facil.isSelected() == true) {
				alterCards();
				layoutAltern.show(cardsPanelAltern, FacilQuestaoPanel.class.getName());
			} else if (medio.isSelected() == true) {
				alterCards();
				layoutAltern.show(cardsPanelAltern, MediaQuestaoPanel.class.getName());
			} else if (dificil.isSelected() == true) {
				alterCards();
				layoutAltern.show(cardsPanelAltern, DificilQuestaoPanel.class.getName());
			}
		}
	}

}