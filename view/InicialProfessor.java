package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InicialProfessor extends JPanel {
    private AppFrame frame;
    private JButton perguntasBtn;
    private LoginProfessorPanel p;

    public InicialProfessor(AppFrame appFrame) {
        this.frame = appFrame;

        perguntasBtn = new JButton("Perguntas");
        // Metodo para "Escutar Ações"
        perguntasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarPerguntas();
            }

        });
        add(perguntasBtn);// Botão Perguntas

        JButton alunosBtn = new JButton("Alunos");
        add(alunosBtn);// Botão Alunos
        JButton turmaBtn = new JButton("Turmas");
        add(turmaBtn);// Botão Turmas
        JButton sairBtn = new JButton("Sair");
        sairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarLoginProfessor();
            }
        });
        add(sairBtn);// Botão Sair

    }

} // end of InicialPanel class.
