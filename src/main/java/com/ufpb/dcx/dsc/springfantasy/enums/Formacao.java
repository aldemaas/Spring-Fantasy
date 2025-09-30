package com.ufpb.dcx.dsc.springfantasy.enums;

public enum Formacao {
    F_4_4_2(4, 4, 2, "4-4-2"),
    F_4_3_3(4, 3, 3, "4-3-3"),
    F_3_5_2(3, 5, 2, "3-5-2"),
    F_5_3_2(5, 3, 2, "5-3-2"),
    F_4_5_1(4, 5, 1, "4-5-1"),
    F_3_4_3(3, 4, 3, "3-4-3");

    private final int defensores;
    private final int meiosCampo;
    private final int atacantes;
    private final String descricao;

    Formacao(int defensores, int meiosCampo, int atacantes, String descricao) {
        this.defensores = defensores;
        this.meiosCampo = meiosCampo;
        this.atacantes = atacantes;
        this.descricao = descricao;
    }

    public int getDefensores() {
        return defensores;
    }

    public int getMeiosCampo() {
        return meiosCampo;
    }

    public int getAtacantes() {
        return atacantes;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getTotalJogadores() {
        return 1 + defensores + meiosCampo + atacantes; // +1 para o goleiro
    }

    public String getResumo() {
        return String.format("%s (GOL: 1, DEF: %d, MEI: %d, ATA: %d)",
            descricao, defensores, meiosCampo, atacantes);
    }

    // Método para compatibilidade (deprecated)
    @Deprecated
    public int getZagueiros() {
        return defensores;
    }

    @Override
    public String toString() {
        return descricao; // Retorna "4-4-2" em vez de "F_4_4_2"
    }

    public static Formacao fromDescricao(String descricao) {
        for (Formacao formacao : values()) {
            if (formacao.descricao.equals(descricao)) {
                return formacao;
            }
        }
        throw new IllegalArgumentException("Descrição inválida para Formacao: " + descricao);
    }
}
