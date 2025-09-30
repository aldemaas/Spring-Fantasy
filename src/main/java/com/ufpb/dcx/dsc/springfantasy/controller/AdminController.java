package com.ufpb.dcx.dsc.springfantasy.controller;

import com.ufpb.dcx.dsc.springfantasy.dto.*;
import com.ufpb.dcx.dsc.springfantasy.enums.RodadaStatus;
import com.ufpb.dcx.dsc.springfantasy.service.ClubeService;
import com.ufpb.dcx.dsc.springfantasy.service.JogadorService;
import com.ufpb.dcx.dsc.springfantasy.service.RodadaService;
import com.ufpb.dcx.dsc.springfantasy.service.PontuacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "Administração", description = "Endpoints administrativos protegidos (apenas ADMIN)")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private ClubeService clubeService;

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private RodadaService rodadaService;

    @Autowired
    private PontuacaoService pontuacaoService;

    @PostMapping("/clubes")
    @Operation(summary = "Adicionar novo clube", description = "Adiciona um novo clube ao sistema")
    public ResponseEntity<ClubeDTO> adicionarClube(@Valid @RequestBody CriarClubeRequest request) {
        ClubeDTO clubeDTO = new ClubeDTO(null, request.getNome());
        ClubeDTO novoClube = clubeService.save(clubeDTO);
        return ResponseEntity.ok(novoClube);
    }

    @PostMapping("/jogadores")
    @Operation(summary = "Adicionar novo jogador", description = "Adiciona um novo jogador ao sistema")
    public ResponseEntity<JogadorDTO> adicionarJogador(@Valid @RequestBody CriarJogadorRequest request) {
        JogadorDTO novoJogador = jogadorService.saveFromRequest(request);
        return ResponseEntity.ok(novoJogador);
    }

    @PostMapping("/rodadas")
    @Operation(summary = "Criar nova rodada", description = "Cria uma nova rodada no sistema")
    public ResponseEntity<RodadaDTO> criarRodada(
            @Parameter(description = "Número da rodada")
            @RequestParam Integer numero) {
        RodadaDTO novaRodada = rodadaService.criarNovaRodada(numero);
        return ResponseEntity.ok(novaRodada);
    }

    @PutMapping("/rodadas/{id}/status")
    @Operation(summary = "Atualizar status da rodada", description = "Atualiza o status de uma rodada específica")
    public ResponseEntity<RodadaDTO> atualizarStatusRodada(
            @Parameter(description = "ID da rodada")
            @PathVariable Long id,
            @Parameter(description = "Novo status da rodada")
            @RequestParam RodadaStatus status) {
        RodadaDTO rodadaAtualizada = rodadaService.atualizarStatusRodada(id, status);
        return ResponseEntity.ok(rodadaAtualizada);
    }

    @PostMapping("/rodadas/{rodadaId}/jogadores/{jogadorId}/pontuacao")
    @Operation(summary = "Registrar pontuação de um jogador", description = "Registra ou atualiza a pontuação de um jogador para uma rodada específica")
    public ResponseEntity<Void> registrarPontuacao(
            @PathVariable Long rodadaId,
            @PathVariable Long jogadorId,
            @Valid @RequestBody RegistrarPontuacaoRequest request) {
        pontuacaoService.registrarPontuacaoJogador(rodadaId, jogadorId, request.getPontos());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rodadas/{rodadaId}/pontuacoes/multiplas")
    @Operation(summary = "Registrar múltiplas pontuações", description = "Registra ou atualiza as pontuações de múltiplos jogadores para uma rodada específica de uma só vez")
    public ResponseEntity<String> registrarMultiplasPontuacoes(
            @Parameter(description = "ID da rodada")
            @PathVariable Long rodadaId,
            @Valid @RequestBody RegistrarMultiplasPontuacoesRequest request) {
        pontuacaoService.registrarMultiplasPontuacoes(rodadaId, request.getPontuacoesPorJogador());
        return ResponseEntity.ok("Pontuações registradas com sucesso para " + request.getPontuacoesPorJogador().size() + " jogadores");
    }

    @PostMapping("/rodadas/{rodadaId}/times/pontuacao")
    @Operation(summary = "Registrar pontuação dos times de fantasia", description = "Calcula e registra a pontuação total de todos os times de fantasia em uma rodada específica.")
    public ResponseEntity<String> registrarPontuacaoTimesFantasia(@PathVariable Long rodadaId) {
        int timesAtualizados = pontuacaoService.registrarPontuacaoTimeFantasia(rodadaId);
        String mensagem = "Pontuações calculadas e registradas com sucesso para " + timesAtualizados + " times de fantasia na rodada " + rodadaId;
        return ResponseEntity.ok(mensagem);
    }

    @PostMapping("/temporada/nova")
    @Operation(summary = "Iniciar nova temporada", description = "Reseta todas as pontuações e inicia uma nova temporada com a rodada 1")
    public ResponseEntity<RodadaDTO> iniciarNovaTemporada() {
        RodadaDTO novaRodada = rodadaService.iniciarNovaTemporada();
        return ResponseEntity.ok(novaRodada);
    }
}
