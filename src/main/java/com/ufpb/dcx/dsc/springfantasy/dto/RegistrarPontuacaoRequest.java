package com.ufpb.dcx.dsc.springfantasy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegistrarPontuacaoRequest {
    @NotNull(message = "A pontuação é obrigatória")
    private BigDecimal pontos;
}
