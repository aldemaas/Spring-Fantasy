package com.ufpb.dcx.dsc.springfantasy.repository;

import com.ufpb.dcx.dsc.springfantasy.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    @Query("SELECT j FROM Jogador j WHERE j.clube.id = :clubeId")
    List<Jogador> findByClube(@Param("clubeId") Long clubeId);

    @Query("SELECT j FROM Jogador j WHERE j.posicao = :posicao")
    List<Jogador> findByPosicao(@Param("posicao") String posicao);

    @Query("SELECT j FROM Jogador j WHERE j.posicao = :posicao AND j.clube.id = :clubeId")
    List<Jogador> findByPosicaoAndClube(@Param("posicao") String posicao, @Param("clubeId") Long clubeId);
}
