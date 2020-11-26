package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InicialProfessor extends JPanel {
    private AppFrame frame;
    private JButton perguntasBtn;
    private LoginProfessorPanel p;

    public InicialProfessor(AppFrame appFrame) {
        setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
        this.frame = appFrame;
        setBackground(new Color(0, 159, 136));
        perguntasBtn = new JButton("Perguntas");
        // Metodo para "Escutar Ações"
        perguntasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarPerguntas();
            }

        });
        perguntasBtn.setPreferredSize(new Dimension(150, 100));
        add(perguntasBtn);// Botão Perguntas

        JButton alunosBtn = new JButton("Alunos");
        alunosBtn.setPreferredSize(new Dimension(150, 100));
        add(alunosBtn);// Botão Alunos
        JButton turmaBtn = new JButton("Turmas");
        turmaBtn.setPreferredSize(new Dimension(150, 100));
        add(turmaBtn);// Botão Turmas
        JButton sairBtn = new JButton("Sair");
        sairBtn.setPreferredSize(new Dimension(150, 100));
        sairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarLoginProfessor();
            }
        });
        add(sairBtn);// Botão Sair

    }

} // end of InicialPanel class.
