package com.ufpb.dcx.dsc.springfantasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontuacaoAcumuladaDTO {

    private Long id;
    private TimeFantasiaDTO timeFantasia;
    private BigDecimal pontuacaoTotal;
    private Integer rodadaAtual;
    private Integer posicao; // Para ranking/classificação
}
