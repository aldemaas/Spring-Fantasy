package com.ufpb.dcx.dsc.springfantasy.service;

import com.ufpb.dcx.dsc.springfantasy.model.*;
import com.ufpb.dcx.dsc.springfantasy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PontuacaoService {

    private final PontuacaoJogadorRepository pontuacaoJogadorRepository;
    private final JogadorRepository jogadorRepository;
    private final RodadaRepository rodadaRepository;
    private final EscalacaoRepository escalacaoRepository;
    private final PontuacaoTimeFantasiaRepository pontuacaoTimeFantasiaRepository;
    private final PontuacaoAcumuladaTimeFantasiaRepository pontuacaoAcumuladaRepository;

    public PontuacaoJogador registrarPontuacaoJogador(Long rodadaId, Long jogadorId, BigDecimal pontos) {
        Rodada rodada = rodadaRepository.findById(rodadaId)
                .orElseThrow(() -> new IllegalArgumentException("Rodada não encontrada"));

        Jogador jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new IllegalArgumentException("Jogador não encontrado"));

        PontuacaoJogador pontuacao = pontuacaoJogadorRepository.findByJogadorAndRodada(jogador, rodada)
                .orElse(new PontuacaoJogador());

        pontuacao.setRodada(rodada);
        pontuacao.setJogador(jogador);
        pontuacao.setPontos(pontos);

        return pontuacaoJogadorRepository.save(pontuacao);
    }

    @Transactional
    public int registrarPontuacaoTimeFantasia(Long rodadaId) {
        Rodada rodada = rodadaRepository.findById(rodadaId)
                .orElseThrow(() -> new IllegalArgumentException("Rodada não encontrada"));

        // Buscar todas as escalações da rodada
        List<Escalacao> escalacoesDaRodada = escalacaoRepository.findByRodada(rodada);

        if (escalacoesDaRodada.isEmpty()) {
            return 0; // Nenhum time teve escalação nesta rodada
        }

        // Agrupar escalações por time fantasia
        Map<TimeFantasia, List<Escalacao>> escalacoesPorTime = escalacoesDaRodada.stream()
                .collect(Collectors.groupingBy(Escalacao::getTimeFantasia));

        int timesAtualizados = 0;

        for (Map.Entry<TimeFantasia, List<Escalacao>> entry : escalacoesPorTime.entrySet()) {
            TimeFantasia timeFantasia = entry.getKey();
            List<Escalacao> escalacoesDoTime = entry.getValue();

            // Calcular pontuação da rodada
            BigDecimal pontuacaoRodada = escalacoesDoTime.stream()
                    .map(escalacao -> pontuacaoJogadorRepository.findByJogadorAndRodada(escalacao.getJogador(), rodada)
                            .map(PontuacaoJogador::getPontos)
                            .orElse(BigDecimal.ZERO))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Salvar pontuação da rodada específica
            PontuacaoTimeFantasia pontuacaoRodadaExistente = pontuacaoTimeFantasiaRepository
                    .findByTimeFantasiaAndRodada(timeFantasia, rodada)
                    .orElse(new PontuacaoTimeFantasia());

            pontuacaoRodadaExistente.setTimeFantasia(timeFantasia);
            pontuacaoRodadaExistente.setRodada(rodada);
            pontuacaoRodadaExistente.setPontos(pontuacaoRodada);
            pontuacaoTimeFantasiaRepository.save(pontuacaoRodadaExistente);

            // Atualizar pontuação acumulada
            atualizarPontuacaoAcumulada(timeFantasia, pontuacaoRodada, rodada.getNumero());

            timesAtualizados++;
        }

        return timesAtualizados;
    }

    @Transactional
    public void atualizarPontuacaoAcumulada(TimeFantasia timeFantasia, BigDecimal pontuacaoRodada, Integer numeroRodada) {
        PontuacaoAcumuladaTimeFantasia pontuacaoAcumulada = pontuacaoAcumuladaRepository
                .findByTimeFantasia(timeFantasia)
                .orElse(new PontuacaoAcumuladaTimeFantasia(timeFantasia));

        // Se for uma rodada anterior à atual, recalcular do zero
        if (numeroRodada <= pontuacaoAcumulada.getRodadaAtual()) {
            recalcularPontuacaoAcumulada(timeFantasia);
        } else {
            // Adicionar pontuação da nova rodada
            pontuacaoAcumulada.adicionarPontos(pontuacaoRodada);
            pontuacaoAcumulada.setRodadaAtual(numeroRodada);
            pontuacaoAcumuladaRepository.save(pontuacaoAcumulada);
        }
    }

    @Transactional
    public void recalcularPontuacaoAcumulada(TimeFantasia timeFantasia) {
        // Buscar todas as pontuações por rodada deste time
        List<PontuacaoTimeFantasia> todasPontuacoes = pontuacaoTimeFantasiaRepository
                .findByTimeFantasiaOrderByRodadaNumeroAsc(timeFantasia);

        BigDecimal pontuacaoTotal = todasPontuacoes.stream()
                .map(PontuacaoTimeFantasia::getPontos)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Integer rodadaAtual = todasPontuacoes.stream()
                .mapToInt(p -> p.getRodada().getNumero())
                .max()
                .orElse(1);

        PontuacaoAcumuladaTimeFantasia pontuacaoAcumulada = pontuacaoAcumuladaRepository
                .findByTimeFantasia(timeFantasia)
                .orElse(new PontuacaoAcumuladaTimeFantasia(timeFantasia));

        pontuacaoAcumulada.setPontuacaoTotal(pontuacaoTotal);
        pontuacaoAcumulada.setRodadaAtual(rodadaAtual);
        pontuacaoAcumuladaRepository.save(pontuacaoAcumulada);
    }

    public void inicializarPontuacaoAcumuladaParaNovoTime(TimeFantasia timeFantasia) {
        if (pontuacaoAcumuladaRepository.findByTimeFantasia(timeFantasia).isEmpty()) {
            PontuacaoAcumuladaTimeFantasia pontuacaoAcumulada = new PontuacaoAcumuladaTimeFantasia(timeFantasia);
            pontuacaoAcumuladaRepository.save(pontuacaoAcumulada);
        }
    }

    public List<PontuacaoJogador> registrarMultiplasPontuacoes(Long rodadaId, Map<Long, BigDecimal> pontuacoesPorJogador) {
        return pontuacoesPorJogador.entrySet().stream()
                .map(entry -> registrarPontuacaoJogador(rodadaId, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
