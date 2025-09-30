package com.ufpb.dcx.dsc.springfantasy.service;

import com.ufpb.dcx.dsc.springfantasy.dto.PontuacaoAcumuladaDTO;
import com.ufpb.dcx.dsc.springfantasy.dto.TimeFantasiaDTO;
import com.ufpb.dcx.dsc.springfantasy.model.PontuacaoAcumuladaTimeFantasia;
import com.ufpb.dcx.dsc.springfantasy.repository.PontuacaoAcumuladaTimeFantasiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassificacaoService {

    private final PontuacaoAcumuladaTimeFantasiaRepository pontuacaoAcumuladaRepository;

    public List<PontuacaoAcumuladaDTO> getClassificacaoGeral() {
        List<PontuacaoAcumuladaTimeFantasia> pontuacoes = pontuacaoAcumuladaRepository.findAllOrderByPontuacaoTotalDesc();

        AtomicInteger posicao = new AtomicInteger(1);

        return pontuacoes.stream()
                .map(pontuacao -> {
                    PontuacaoAcumuladaDTO dto = new PontuacaoAcumuladaDTO();
                    dto.setId(pontuacao.getId());
                    dto.setTimeFantasia(convertTimeFantasiaToDTO(pontuacao.getTimeFantasia()));
                    dto.setPontuacaoTotal(pontuacao.getPontuacaoTotal());
                    dto.setRodadaAtual(pontuacao.getRodadaAtual());
                    dto.setPosicao(posicao.getAndIncrement());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private TimeFantasiaDTO convertTimeFantasiaToDTO(com.ufpb.dcx.dsc.springfantasy.model.TimeFantasia timeFantasia) {
        TimeFantasiaDTO dto = new TimeFantasiaDTO();
        dto.setId(timeFantasia.getId());
        dto.setNome(timeFantasia.getNome());
        dto.setPatrimonio(timeFantasia.getPatrimonio());
        // Corrigindo o nome do campo para nomeUsuario
        if (timeFantasia.getUsuario() != null) {
            dto.setNomeUsuario(timeFantasia.getUsuario().getUsername());
        }
        return dto;
    }
}
