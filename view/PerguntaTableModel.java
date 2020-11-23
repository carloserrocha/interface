package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Questao;

public class PerguntaTableModel extends AbstractTableModel {
    private List<Questao> questoes = new ArrayList<Questao>();
    private String[] colunas = new String[] { "id", "Pergunta", "Dificuldade" };

    public PerguntaTableModel(List<Questao> questoes) {
        this.questoes = questoes;
    }

    @Override
    public int getRowCount() {
        return questoes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        String columnName = null;
        if (column >= 0 && column < colunas.length) {
            columnName = colunas[column];
        }
        return columnName;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String value = null;

        if (rowIndex >= 0 && rowIndex < questoes.size()) {
            Questao questao = questoes.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    value = Integer.toString(questao.getId());
                    break;
                case 1:
                    value = questao.getPergunta();
                    break;
                case 2:
                    value = questao.getDescricao();
                    break;
                default:
                    System.err.printf("[ERRO] Indice de coluna inválido: %d\n", columnIndex);
                    break;
            }
        }

        return value;
    }

    public void carregar(List<Questao> questoes) {
        this.questoes = questoes;
        fireTableDataChanged();
    }

    public Questao getQuestao(int rowIndex) {
        Questao questao = null;

        if (rowIndex >= 0 && rowIndex < questoes.size()) {
            questao = questoes.get(rowIndex);
        }

        return questao;
    }

    public void remover(Questao questao) {
        questoes.remove(questao);
        fireTableDataChanged();
    }

}
