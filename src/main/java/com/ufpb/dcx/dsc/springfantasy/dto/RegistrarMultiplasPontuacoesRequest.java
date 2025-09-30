package com.ufpb.dcx.dsc.springfantasy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Schema(description = "Requisição para registrar múltiplas pontuações de jogadores em uma rodada")
public class RegistrarMultiplasPontuacoesRequest {

    @Schema(
        description = "Mapa com as pontuações dos jogadores. Chave: ID do jogador, Valor: Pontuação obtida",
        example = "{\"idjogador\": 8.5, \"idjogador\": 9.0, \"idjogador\": 12.0}",
        additionalProperties = Schema.AdditionalPropertiesValue.TRUE
    )
    @NotNull(message = "O mapa de pontuações é obrigatório")
    @NotEmpty(message = "Deve haver pelo menos uma pontuação para registrar")
    private Map<Long, BigDecimal> pontuacoesPorJogador;
}
