package com.ufpb.dcx.dsc.springfantasy.repository;

import com.ufpb.dcx.dsc.springfantasy.model.PontuacaoAcumuladaTimeFantasia;
import com.ufpb.dcx.dsc.springfantasy.model.TimeFantasia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PontuacaoAcumuladaTimeFantasiaRepository extends JpaRepository<PontuacaoAcumuladaTimeFantasia, Long> {

    Optional<PontuacaoAcumuladaTimeFantasia> findByTimeFantasia(TimeFantasia timeFantasia);

    @Query("SELECT p FROM PontuacaoAcumuladaTimeFantasia p ORDER BY p.pontuacaoTotal DESC")
    List<PontuacaoAcumuladaTimeFantasia> findAllOrderByPontuacaoTotalDesc();

    @Query("SELECT COUNT(p) FROM PontuacaoAcumuladaTimeFantasia p WHERE p.rodadaAtual > 38")
    long countTimesComMaisDe38Rodadas();

    @Query("SELECT p FROM PontuacaoAcumuladaTimeFantasia p WHERE p.rodadaAtual = ?1")
    List<PontuacaoAcumuladaTimeFantasia> findByRodadaAtual(Integer rodada);
}
