package com.ufpb.dcx.dsc.springfantasy.dto;

import com.ufpb.dcx.dsc.springfantasy.enums.Formacao;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscalacaoRequest {

    @NotEmpty(message = "Lista de jogadores não pode estar vazia")
    @Size(min = 11, max = 11, message = "Escalação deve conter exatamente 11 jogadores")
    private List<Long> jogadorIds;

    @NotNull(message = "Formação é obrigatória")
    private Formacao formacao;
}
