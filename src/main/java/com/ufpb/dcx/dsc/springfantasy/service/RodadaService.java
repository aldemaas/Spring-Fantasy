package com.ufpb.dcx.dsc.springfantasy.service;

import com.ufpb.dcx.dsc.springfantasy.dto.RodadaDTO;
import com.ufpb.dcx.dsc.springfantasy.enums.RodadaStatus;
import com.ufpb.dcx.dsc.springfantasy.model.Rodada;
import com.ufpb.dcx.dsc.springfantasy.model.PontuacaoAcumuladaTimeFantasia;
import com.ufpb.dcx.dsc.springfantasy.model.TimeFantasia;
import com.ufpb.dcx.dsc.springfantasy.repository.RodadaRepository;
import com.ufpb.dcx.dsc.springfantasy.repository.PontuacaoAcumuladaTimeFantasiaRepository;
import com.ufpb.dcx.dsc.springfantasy.repository.TimeFantasiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RodadaService {

    private static final int LIMITE_RODADAS = 38;

    @Autowired
    private RodadaRepository rodadaRepository;

    @Autowired
    private PontuacaoAcumuladaTimeFantasiaRepository pontuacaoAcumuladaRepository;

    @Autowired
    private TimeFantasiaRepository timeFantasiaRepository;

    public List<RodadaDTO> findAll() {
        return rodadaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RodadaDTO findRodadaAtual() {
        Rodada rodada = rodadaRepository.findFirstByOrderByNumeroDesc()
                .orElseThrow(() -> new RuntimeException("Nenhuma rodada encontrada"));
        return convertToDTO(rodada);
    }

    public RodadaDTO findById(Long id) {
        Rodada rodada = rodadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rodada não encontrada com ID: " + id));
        return convertToDTO(rodada);
    }

    @Transactional
    public RodadaDTO criarNovaRodada(Integer numero) {
        if (numero > LIMITE_RODADAS) {
            throw new RuntimeException("Não é possível criar rodadas além da rodada " + LIMITE_RODADAS);
        }

        if (rodadaRepository.findByNumero(numero).isPresent()) {
            throw new RuntimeException("Já existe uma rodada com o número: " + numero);
        }

        Rodada rodada = new Rodada();
        rodada.setNumero(numero);
        rodada.setStatus(RodadaStatus.MERCADO_ABERTO);

        Rodada savedRodada = rodadaRepository.save(rodada);

        // Se for a primeira rodada (rodada 1), inicializar pontuações acumuladas para todos os times
        if (numero == 1) {
            inicializarPontuacoesAcumuladas();
        }

        return convertToDTO(savedRodada);
    }

    @Transactional
    public RodadaDTO iniciarNovaTemporada() {
        // Reset todas as pontuações acumuladas
        resetarTodasPontuacoesAcumuladas();

        // Criar rodada 1 da nova temporada
        return criarNovaRodada(1);
    }

    public RodadaDTO atualizarStatusRodada(Long id, RodadaStatus novoStatus) {
        Rodada rodada = rodadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rodada não encontrada com ID: " + id));

        rodada.setStatus(novoStatus);
        Rodada updatedRodada = rodadaRepository.save(rodada);
        return convertToDTO(updatedRodada);
    }

    public boolean isMercadoAberto() {
        return rodadaRepository.findFirstByOrderByNumeroDesc()
                .map(rodada -> rodada.getStatus() == RodadaStatus.MERCADO_ABERTO)
                .orElse(false);
    }

    private void inicializarPontuacoesAcumuladas() {
        List<TimeFantasia> todosOsTimes = timeFantasiaRepository.findAll();

        for (TimeFantasia time : todosOsTimes) {
            if (pontuacaoAcumuladaRepository.findByTimeFantasia(time).isEmpty()) {
                PontuacaoAcumuladaTimeFantasia pontuacaoAcumulada = new PontuacaoAcumuladaTimeFantasia(time);
                pontuacaoAcumuladaRepository.save(pontuacaoAcumulada);
            }
        }
    }

    private void resetarTodasPontuacoesAcumuladas() {
        List<PontuacaoAcumuladaTimeFantasia> todasPontuacoes = pontuacaoAcumuladaRepository.findAll();

        for (PontuacaoAcumuladaTimeFantasia pontuacao : todasPontuacoes) {
            pontuacao.resetarPontuacao();
            pontuacaoAcumuladaRepository.save(pontuacao);
        }
    }

    public boolean isTemporadaCompleta() {
        return rodadaRepository.findFirstByOrderByNumeroDesc()
                .map(rodada -> rodada.getNumero() >= LIMITE_RODADAS)
                .orElse(false);
    }

    public Integer getRodadaAtualNumero() {
        return rodadaRepository.findFirstByOrderByNumeroDesc()
                .map(Rodada::getNumero)
                .orElse(0);
    }

    private RodadaDTO convertToDTO(Rodada rodada) {
        return new RodadaDTO(
                rodada.getId(),
                rodada.getNumero(),
                rodada.getStatus()
        );
    }
}
