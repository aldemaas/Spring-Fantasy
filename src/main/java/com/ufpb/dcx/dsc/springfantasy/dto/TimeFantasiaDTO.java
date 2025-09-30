package com.ufpb.dcx.dsc.springfantasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeFantasiaDTO {

    private Long id;
    private String nome;
    private BigDecimal patrimonio;
    private String nomeUsuario;
    private List<JogadorDTO> escalacao;
}
