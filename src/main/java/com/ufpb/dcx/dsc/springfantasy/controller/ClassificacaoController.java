package com.ufpb.dcx.dsc.springfantasy.controller;

import com.ufpb.dcx.dsc.springfantasy.dto.PontuacaoAcumuladaDTO;
import com.ufpb.dcx.dsc.springfantasy.service.ClassificacaoService;
import com.ufpb.dcx.dsc.springfantasy.service.RodadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classificacao")
@Tag(name = "Classificação", description = "Endpoints públicos para consultar classificação e pontuações acumuladas")
public class ClassificacaoController {

    @Autowired
    private ClassificacaoService classificacaoService;

    @Autowired
    private RodadaService rodadaService;

    @GetMapping
    @Operation(summary = "Obter classificação geral com pontuações acumuladas",
               description = "Retorna o ranking geral dos times fantasia ordenados por pontuação total acumulada")
    public ResponseEntity<List<PontuacaoAcumuladaDTO>> getClassificacaoGeral() {
        List<PontuacaoAcumuladaDTO> classificacao = classificacaoService.getClassificacaoGeral();
        return ResponseEntity.ok(classificacao);
    }

    @GetMapping("/temporada/status")
    @Operation(summary = "Status da temporada atual",
               description = "Retorna informações sobre o progresso da temporada atual")
    public ResponseEntity<String> getStatusTemporada() {
        boolean temporadaCompleta = rodadaService.isTemporadaCompleta();
        Integer rodadaAtual = rodadaService.getRodadaAtualNumero();

        String status;
        if (temporadaCompleta) {
            status = String.format("Temporada completa! Todas as 38 rodadas foram concluídas. Rodada atual: %d/38", rodadaAtual);
        } else if (rodadaAtual > 0) {
            status = String.format("Temporada em andamento. Rodada atual: %d/38 (%.1f%% concluído)",
                    rodadaAtual, (rodadaAtual / 38.0) * 100);
        } else {
            status = "Temporada não iniciada. Nenhuma rodada foi criada ainda.";
        }

        return ResponseEntity.ok(status);
    }
}
