package model;

public enum Dificuldade {
    FACIL("Fácil"), MEDIO("Médio"), DIFICIL("Difícil");

    private String descricao;

    Dificuldade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
