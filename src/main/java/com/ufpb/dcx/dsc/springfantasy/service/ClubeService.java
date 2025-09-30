package com.ufpb.dcx.dsc.springfantasy.service;

import com.ufpb.dcx.dsc.springfantasy.dto.ClubeDTO;
import com.ufpb.dcx.dsc.springfantasy.model.Clube;
import com.ufpb.dcx.dsc.springfantasy.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    public List<ClubeDTO> findAll() {
        return clubeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClubeDTO findById(Long id) {
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado com ID: " + id));
        return convertToDTO(clube);
    }

    public ClubeDTO save(ClubeDTO clubeDTO) {
        if (clubeRepository.existsByNome(clubeDTO.getNome())) {
            throw new RuntimeException("Já existe um clube com este nome!");
        }

        Clube clube = new Clube();
        clube.setNome(clubeDTO.getNome());

        Clube savedClube = clubeRepository.save(clube);
        return convertToDTO(savedClube);
    }

    private ClubeDTO convertToDTO(Clube clube) {
        return new ClubeDTO(clube.getId(), clube.getNome());
    }
}
