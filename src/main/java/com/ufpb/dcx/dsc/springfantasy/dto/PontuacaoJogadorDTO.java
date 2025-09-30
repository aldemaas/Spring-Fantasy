package com.ufpb.dcx.dsc.springfantasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PontuacaoJogadorDTO {
    private Long jogadorId;
    private Long rodadaId;
    private BigDecimal pontos;
    private String nomeJogador;
    private Integer numeroRodada;
}
