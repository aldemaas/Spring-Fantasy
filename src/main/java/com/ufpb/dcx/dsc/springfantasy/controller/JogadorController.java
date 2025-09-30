package com.ufpb.dcx.dsc.springfantasy.controller;

import com.ufpb.dcx.dsc.springfantasy.dto.JogadorDTO;
import com.ufpb.dcx.dsc.springfantasy.service.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
@Tag(name = "Jogadores", description = "Endpoints públicos para consultar jogadores disponíveis")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    @Operation(summary = "Listar todos os jogadores",
               description = "Lista todos os jogadores disponíveis no mercado. Pode incluir filtros por posição e clube")
    public ResponseEntity<List<JogadorDTO>> getAllJogadores(
            @Parameter(description = "Filtrar por posição do jogador (aceita maiúscula/minúscula: GOL, DEF, MEI, ATA)")
            @RequestParam(required = false) String posicao,
            @Parameter(description = "Filtrar por ID do clube")
            @RequestParam(required = false, name = "clubeId") Long clubeId) {

        List<JogadorDTO> jogadores;

        if (posicao != null) {
            posicao = posicao.toUpperCase().trim();
        }

        if (posicao != null && clubeId != null) {
            jogadores = jogadorService.findByPosicaoAndClube(posicao, clubeId);
        } else if (posicao != null) {
            jogadores = jogadorService.findByPosicao(posicao);
        } else if (clubeId != null) {
            jogadores = jogadorService.findByClube(clubeId);
        } else {
            jogadores = jogadorService.findAll();
        }

        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar jogador por ID", description = "Retorna os detalhes de um jogador específico")
    public ResponseEntity<JogadorDTO> getJogadorById(
            @Parameter(description = "ID do jogador")
            @PathVariable Long id) {
        JogadorDTO jogador = jogadorService.findById(id);
        return ResponseEntity.ok(jogador);
    }
}
