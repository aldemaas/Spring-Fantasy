package com.ufpb.dcx.dsc.springfantasy.controller;

import com.ufpb.dcx.dsc.springfantasy.dto.CriarTimeFantasiaRequest;
import com.ufpb.dcx.dsc.springfantasy.dto.EscalacaoRequest;
import com.ufpb.dcx.dsc.springfantasy.dto.TimeFantasiaDTO;
import com.ufpb.dcx.dsc.springfantasy.enums.Formacao;
import com.ufpb.dcx.dsc.springfantasy.service.TimeFantasiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times-fantasia")
@Tag(name = "Times Fantasia", description = "Endpoints protegidos para gerenciar times fantasia")
@SecurityRequirement(name = "Bearer Authentication")
public class TimeFantasiaController {

    @Autowired
    private TimeFantasiaService timeFantasiaService;

    @PostMapping
    @Operation(summary = "Criar time fantasia", description = "Cria um time fantasia para o usuário autenticado")
    public ResponseEntity<TimeFantasiaDTO> criarTime(
            @Valid @RequestBody CriarTimeFantasiaRequest request,
            Authentication authentication) {
        TimeFantasiaDTO timeFantasia = timeFantasiaService.criarTime(request, authentication);
        return ResponseEntity.ok(timeFantasia);
    }

    @GetMapping("/me")
    @Operation(summary = "Obter meu time fantasia", description = "Retorna o time fantasia e escalação do usuário autenticado")
    public ResponseEntity<TimeFantasiaDTO> getMeuTime(Authentication authentication) {
        TimeFantasiaDTO timeFantasia = timeFantasiaService.findByUsuario(authentication);
        return ResponseEntity.ok(timeFantasia);
    }

    @PutMapping("/me/escalacao")
    @Operation(summary = "Atualizar escalação",
               description = "Atualiza a escalação do time para a rodada atual com uma formação tática específica. Selecione a formação desejada no dropdown.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Escalação atualizada com sucesso",
                content = @Content(schema = @Schema(implementation = TimeFantasiaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Formação inválida ou escalação incorreta"),
        @ApiResponse(responseCode = "403", description = "Mercado fechado ou sem permissão")
    })
    public ResponseEntity<TimeFantasiaDTO> atualizarEscalacao(
            @Valid @RequestBody List<Long> jogadorIds,
            @RequestParam Formacao formacao,
            Authentication authentication) {

        // Criar o request object internamente
        EscalacaoRequest request = new EscalacaoRequest();
        request.setJogadorIds(jogadorIds);
        request.setFormacao(formacao);
        TimeFantasiaDTO timeFantasia = timeFantasiaService.atualizarEscalacao(request, authentication);
        return ResponseEntity.ok(timeFantasia);
    }
}
