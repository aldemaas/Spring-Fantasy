package com.ufpb.dcx.dsc.springfantasy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarTimeFantasiaRequest {

    @NotBlank(message = "Nome do time é obrigatório")
    @Size(min = 3, max = 30, message = "Nome do time deve ter entre 3 e 30 caracteres")
    private String nome;
}
