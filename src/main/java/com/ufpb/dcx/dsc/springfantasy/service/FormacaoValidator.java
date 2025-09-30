package com.ufpb.dcx.dsc.springfantasy.service;

import com.ufpb.dcx.dsc.springfantasy.enums.Formacao;
import com.ufpb.dcx.dsc.springfantasy.model.Jogador;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FormacaoValidator {

    public void validarFormacao(List<Jogador> jogadores, Formacao formacao) {
        if (jogadores == null || jogadores.isEmpty()) {
            throw new IllegalArgumentException("Lista de jogadores não pode estar vazia");
        }

        // Contar jogadores por posição
        Map<String, Long> contagemPorPosicao = jogadores.stream()
            .collect(Collectors.groupingBy(Jogador::getPosicao, Collectors.counting()));

        long goleiros = contagemPorPosicao.getOrDefault("GOL", 0L);
        long defensores = contagemPorPosicao.getOrDefault("DEF", 0L);
        long meiosCampo = contagemPorPosicao.getOrDefault("MEI", 0L);
        long atacantes = contagemPorPosicao.getOrDefault("ATA", 0L);

        // Validar se tem exatamente 1 goleiro
        if (goleiros != 1) {
            throw new IllegalArgumentException("Deve ter exatamente 1 goleiro na escalação");
        }

        // Validar se a formação está correta
        if (defensores != formacao.getDefensores()) {
            throw new IllegalArgumentException(
                String.format("Formação %s requer %d defensores, mas foram escalados %d",
                    formacao.getDescricao(), formacao.getDefensores(), defensores));
        }

        if (meiosCampo != formacao.getMeiosCampo()) {
            throw new IllegalArgumentException(
                String.format("Formação %s requer %d meio-campistas, mas foram escalados %d",
                    formacao.getDescricao(), formacao.getMeiosCampo(), meiosCampo));
        }

        if (atacantes != formacao.getAtacantes()) {
            throw new IllegalArgumentException(
                String.format("Formação %s requer %d atacantes, mas foram escalados %d",
                    formacao.getDescricao(), formacao.getAtacantes(), atacantes));
        }

        // Validar total de jogadores (deve ser exatamente 11)
        if (jogadores.size() != 11) {
            throw new IllegalArgumentException(
                String.format("Uma escalação deve ter exatamente 11 jogadores, mas foram escalados %d",
                    jogadores.size()));
        }

        // Validar se não há jogadores duplicados
        long jogadoresUnicos = jogadores.stream()
            .map(Jogador::getId)
            .distinct()
            .count();

        if (jogadoresUnicos != jogadores.size()) {
            throw new IllegalArgumentException("Não é possível escalar o mesmo jogador mais de uma vez");
        }
    }

    public List<Formacao> getFormacoesDisponiveis() {
        return Arrays.asList(Formacao.values());
    }

    public String getDescricaoFormacao(Formacao formacao) {
        return String.format("%s - GOL: 1, DEF: %d, MEI: %d, ATA: %d",
            formacao.getDescricao(),
            formacao.getDefensores(),
            formacao.getMeiosCampo(),
            formacao.getAtacantes());
    }
}
