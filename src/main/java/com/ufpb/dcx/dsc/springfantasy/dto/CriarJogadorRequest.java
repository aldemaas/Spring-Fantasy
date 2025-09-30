package com.ufpb.dcx.dsc.springfantasy.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarJogadorRequest {

    @NotBlank(message = "Nome do jogador é obrigatório")
    @Size(min = 2, max = 100, message = "Nome do jogador deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Posição é obrigatória")
    @Pattern(regexp = "^(GOL|ZAG|LAT|MEI|ATA)$", message = "Posição deve ser: GOL, ZAG, LAT, MEI ou ATA")
    private String posicao;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    @DecimalMax(value = "50.00", message = "Preço não pode ser maior que 50.00")
    private BigDecimal preco;

    @NotNull(message = "ID do clube é obrigatório")
    @Min(value = 1, message = "ID do clube deve ser válido")
    private Long clubeId;
}
