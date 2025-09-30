package com.ufpb.dcx.dsc.springfantasy.dto;

import com.ufpb.dcx.dsc.springfantasy.enums.RodadaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RodadaDTO {

    private Long id;
    private Integer numero;
    private RodadaStatus status;
}
