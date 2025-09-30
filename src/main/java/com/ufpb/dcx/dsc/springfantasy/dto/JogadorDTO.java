package com.ufpb.dcx.dsc.springfantasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorDTO {

    private Long id;
    private String nome;
    private String posicao;
    private BigDecimal preco;
    private ClubeDTO clube;
}
