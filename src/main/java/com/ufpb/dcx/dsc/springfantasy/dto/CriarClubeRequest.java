package com.ufpb.dcx.dsc.springfantasy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarClubeRequest {

    @NotBlank(message = "Nome do clube é obrigatório")
    @Size(min = 2, max = 50, message = "Nome do clube deve ter entre 2 e 50 caracteres")
    private String nome;
}
