package com.ufpb.dcx.dsc.springfantasy.controller;

import com.ufpb.dcx.dsc.springfantasy.dto.ClubeDTO;
import com.ufpb.dcx.dsc.springfantasy.service.ClubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubes")
@Tag(name = "Clubes", description = "Endpoints públicos para consultar clubes do campeonato")
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @GetMapping
    @Operation(summary = "Listar todos os clubes", description = "Lista todos os clubes do campeonato")
    public ResponseEntity<List<ClubeDTO>> getAllClubes() {
        List<ClubeDTO> clubes = clubeService.findAll();
        return ResponseEntity.ok(clubes);
    }
}
