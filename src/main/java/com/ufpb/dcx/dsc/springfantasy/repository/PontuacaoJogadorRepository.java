package com.ufpb.dcx.dsc.springfantasy.repository;

import com.ufpb.dcx.dsc.springfantasy.model.PontuacaoJogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PontuacaoJogadorRepository extends JpaRepository<PontuacaoJogador, Long> {

    @Query("SELECT pj FROM PontuacaoJogador pj WHERE pj.rodada.id = :rodadaId")
    List<PontuacaoJogador> findByRodada(@Param("rodadaId") Long rodadaId);

    @Query("SELECT pj FROM PontuacaoJogador pj WHERE pj.jogador.id = :jogadorId")
    List<PontuacaoJogador> findByJogador(@Param("jogadorId") Long jogadorId);

    @Query("SELECT pj FROM PontuacaoJogador pj WHERE pj.jogador.id = :jogadorId AND pj.rodada.id = :rodadaId")
    Optional<PontuacaoJogador> findByJogadorAndRodada(@Param("jogadorId") Long jogadorId, @Param("rodadaId") Long rodadaId);

    Optional<PontuacaoJogador> findByJogadorAndRodada(com.ufpb.dcx.dsc.springfantasy.model.Jogador jogador, com.ufpb.dcx.dsc.springfantasy.model.Rodada rodada);

    List<PontuacaoJogador> findByRodada(com.ufpb.dcx.dsc.springfantasy.model.Rodada rodada);
}
