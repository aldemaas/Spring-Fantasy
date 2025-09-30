package com.ufpb.dcx.dsc.springfantasy.controller;

import com.ufpb.dcx.dsc.springfantasy.dto.RodadaDTO;
import com.ufpb.dcx.dsc.springfantasy.service.RodadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rodadas")
@Tag(name = "Rodadas", description = "Endpoints públicos para consultar informações das rodadas")
public class RodadaController {

    @Autowired
    private RodadaService rodadaService;

    @GetMapping("/atual")
    @Operation(summary = "Obter rodada atual", description = "Retorna informações da rodada atual, incluindo status do mercado")
    public ResponseEntity<RodadaDTO> getRodadaAtual() {
        RodadaDTO rodada = rodadaService.findRodadaAtual();
        return ResponseEntity.ok(rodada);
    }
}
