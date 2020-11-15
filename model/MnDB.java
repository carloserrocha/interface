package model;

import java.util.ArrayList;
import java.util.List;

public class MnDB {
    private static int incremento = 0;
    private static List<Questao> questoes = new ArrayList<>();

    public static void inserir(Questao questao) {
        questao.setId(++incremento);
        questoes.add(questao);
    }

    public static void atualizar(Questao questao) {
        int i = questoes.indexOf(questao);
        if (i >= 0) {
            questoes.set(i, questao);
        }
    }

    public static List<Questao> listar() {
        return questoes;
    }
}
