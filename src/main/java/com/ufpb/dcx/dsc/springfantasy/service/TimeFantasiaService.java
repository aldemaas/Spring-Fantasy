package com.ufpb.dcx.dsc.springfantasy.service;

import com.ufpb.dcx.dsc.springfantasy.dto.*;
import com.ufpb.dcx.dsc.springfantasy.exception.*;
import com.ufpb.dcx.dsc.springfantasy.model.*;
import com.ufpb.dcx.dsc.springfantasy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeFantasiaService {

    @Autowired
    private TimeFantasiaRepository timeFantasiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private RodadaRepository rodadaRepository;

    @Autowired
    private EscalacaoRepository escalacaoRepository;

    @Autowired
    private RodadaService rodadaService;

    @Autowired
    private FormacaoValidator formacaoValidator;

    @Autowired
    private PontuacaoService pontuacaoService;

    @Transactional
    public TimeFantasiaDTO criarTime(CriarTimeFantasiaRequest request, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        if (timeFantasiaRepository.findByUsuario(usuario).isPresent()) {
            throw new TimeFantasiaException("Usuário já possui um time fantasia");
        }

        if (timeFantasiaRepository.existsByNome(request.getNome())) {
            throw new TimeFantasiaException("Já existe um time com este nome");
        }

        TimeFantasia timeFantasia = new TimeFantasia();
        timeFantasia.setNome(request.getNome());
        timeFantasia.setUsuario(usuario);
        timeFantasia.setPatrimonio(new BigDecimal("100.0")); // Orçamento inicial

        TimeFantasia savedTime = timeFantasiaRepository.save(timeFantasia);

        // Inicializar pontuação acumulada para o novo time
        pontuacaoService.inicializarPontuacaoAcumuladaParaNovoTime(savedTime);

        return convertToDTO(savedTime);
    }

    public TimeFantasiaDTO findByUsuario(Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        TimeFantasia timeFantasia = timeFantasiaRepository.findByUsuario(usuario)
                .orElseThrow(() -> new TimeFantasiaException("Time fantasia não encontrado para este usuário"));

        return convertToDTOWithEscalacao(timeFantasia);
    }

    @Transactional
    public TimeFantasiaDTO atualizarEscalacao(EscalacaoRequest request, Authentication authentication) {
        if (!rodadaService.isMercadoAberto()) {
            throw new MercadoFechadoException("Mercado fechado! Não é possível alterar a escalação");
        }

        Usuario usuario = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        TimeFantasia timeFantasia = timeFantasiaRepository.findByUsuario(usuario)
                .orElseThrow(() -> new TimeFantasiaException("Time fantasia não encontrado"));

        Rodada rodadaAtual = rodadaRepository.findFirstByOrderByNumeroDesc()
                .orElseThrow(() -> new TimeFantasiaException("Nenhuma rodada ativa encontrada"));

        // Buscar jogadores
        List<Jogador> jogadores = jogadorRepository.findAllById(request.getJogadorIds());
        if (jogadores.size() != 11) {
            throw new NumeroDeJogadoresInvalidoException("Você deve escalar exatamente 11 jogadores");
        }

        // NOVA VALIDAÇÃO DE FORMAÇÃO
        try {
            formacaoValidator.validarFormacao(jogadores, request.getFormacao());
        } catch (IllegalArgumentException e) {
            throw new FormacaoInvalidaException("Formação inválida: " + e.getMessage());
        }

        // Validar se o orçamento permite a escalação
        BigDecimal custoTotal = jogadores.stream()
                .map(Jogador::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (custoTotal.compareTo(timeFantasia.getPatrimonio()) > 0) {
            throw new OrcamentoInsuficienteException("Orçamento insuficiente para esta escalação", custoTotal, timeFantasia.getPatrimonio());
        }

        // Remover escalação anterior da rodada atual
        escalacaoRepository.deleteByTimeFantasiaAndRodada(timeFantasia.getId(), rodadaAtual.getId());

        // Criar nova escalação
        for (Jogador jogador : jogadores) {
            Escalacao escalacao = new Escalacao();
            escalacao.setTimeFantasia(timeFantasia);
            escalacao.setJogador(jogador);
            escalacao.setRodada(rodadaAtual);
            escalacaoRepository.save(escalacao);
        }

        return convertToDTOWithEscalacao(timeFantasia);
    }

    public List<TimeFantasiaDTO> getClassificacao() {
        return timeFantasiaRepository.findAllOrderByPatrimonioDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TimeFantasiaDTO convertToDTO(TimeFantasia timeFantasia) {
        return new TimeFantasiaDTO(
                timeFantasia.getId(),
                timeFantasia.getNome(),
                timeFantasia.getPatrimonio(),
                timeFantasia.getUsuario().getNome(),
                null
        );
    }

    private TimeFantasiaDTO convertToDTOWithEscalacao(TimeFantasia timeFantasia) {
        Rodada rodadaAtual = rodadaRepository.findFirstByOrderByNumeroDesc().orElse(null);
        List<JogadorDTO> escalacao = null;

        if (rodadaAtual != null) {
            List<Escalacao> escalacoes = escalacaoRepository.findByTimeFantasiaAndRodada(
                    timeFantasia.getId(), rodadaAtual.getId());

            escalacao = escalacoes.stream()
                    .map(e -> convertJogadorToDTO(e.getJogador()))
                    .collect(Collectors.toList());
        }

        return new TimeFantasiaDTO(
                timeFantasia.getId(),
                timeFantasia.getNome(),
                timeFantasia.getPatrimonio(),
                timeFantasia.getUsuario().getNome(),
                escalacao
        );
    }

    private JogadorDTO convertJogadorToDTO(Jogador jogador) {
        ClubeDTO clubeDTO = new ClubeDTO(jogador.getClube().getId(), jogador.getClube().getNome());
        return new JogadorDTO(
                jogador.getId(),
                jogador.getNome(),
                jogador.getPosicao(),
                jogador.getPreco(),
                clubeDTO
        );
    }
}
