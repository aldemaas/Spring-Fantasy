package com.ufpb.dcx.dsc.springfantasy.service;

import com.ufpb.dcx.dsc.springfantasy.dto.ClubeDTO;
import com.ufpb.dcx.dsc.springfantasy.dto.CriarJogadorRequest;
import com.ufpb.dcx.dsc.springfantasy.dto.JogadorDTO;
import com.ufpb.dcx.dsc.springfantasy.model.Clube;
import com.ufpb.dcx.dsc.springfantasy.model.Jogador;
import com.ufpb.dcx.dsc.springfantasy.repository.ClubeRepository;
import com.ufpb.dcx.dsc.springfantasy.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private ClubeRepository clubeRepository;

    public List<JogadorDTO> findAll() {
        return jogadorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JogadorDTO> findByPosicao(String posicao) {
        return jogadorRepository.findByPosicao(posicao)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JogadorDTO> findByClube(Long clubeId) {
        return jogadorRepository.findByClube(clubeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JogadorDTO> findByPosicaoAndClube(String posicao, Long clubeId) {
        return jogadorRepository.findByPosicaoAndClube(posicao, clubeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JogadorDTO findById(Long id) {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com ID: " + id));
        return convertToDTO(jogador);
    }

    public JogadorDTO save(JogadorDTO jogadorDTO) {
        Clube clube = clubeRepository.findById(jogadorDTO.getClube().getId())
                .orElseThrow(() -> new RuntimeException("Clube não encontrado"));

        Jogador jogador = new Jogador();
        jogador.setNome(jogadorDTO.getNome());
        jogador.setPosicao(jogadorDTO.getPosicao());
        jogador.setPreco(jogadorDTO.getPreco());
        jogador.setClube(clube);

        Jogador savedJogador = jogadorRepository.save(jogador);
        return convertToDTO(savedJogador);
    }

    public JogadorDTO saveFromRequest(CriarJogadorRequest request) {
        Clube clube = clubeRepository.findById(request.getClubeId())
                .orElseThrow(() -> new RuntimeException("Clube não encontrado com ID: " + request.getClubeId()));

        Jogador jogador = new Jogador();
        jogador.setNome(request.getNome());
        jogador.setPosicao(request.getPosicao());
        jogador.setPreco(request.getPreco());
        jogador.setClube(clube);

        Jogador savedJogador = jogadorRepository.save(jogador);
        return convertToDTO(savedJogador);
    }

    private JogadorDTO convertToDTO(Jogador jogador) {
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
