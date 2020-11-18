package model;

public enum Dificuldade {
    FACIL("Fácil"), MEDIO("Médio"), DIFICIL("Dificil");

    private String descricao;

    Dificuldade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
