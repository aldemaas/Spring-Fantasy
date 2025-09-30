package com.ufpb.dcx.dsc.springfantasy.enums;

public enum Posicao {
    GOLEIRO("GOL", "Goleiro"),
    DEFENSOR("DEF", "Defensor"),
    MEIO_CAMPO("MEI", "Meio-campo"),
    ATACANTE("ATA", "Atacante");

    private final String codigo;
    private final String descricao;

    Posicao(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Posicao fromCodigo(String codigo) {
        for (Posicao posicao : values()) {
            if (posicao.codigo.equals(codigo)) {
                return posicao;
            }
        }
        throw new IllegalArgumentException("Posição não encontrada: " + codigo);
    }
}
