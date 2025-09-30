package com.ufpb.dcx.dsc.springfantasy.repository;

import com.ufpb.dcx.dsc.springfantasy.model.PontuacaoTimeFantasia;
import com.ufpb.dcx.dsc.springfantasy.model.TimeFantasia;
import com.ufpb.dcx.dsc.springfantasy.model.Rodada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PontuacaoTimeFantasiaRepository extends JpaRepository<PontuacaoTimeFantasia, Long> {

    Optional<PontuacaoTimeFantasia> findByTimeFantasiaAndRodada(TimeFantasia timeFantasia, Rodada rodada);

    @Query("SELECT p FROM PontuacaoTimeFantasia p WHERE p.timeFantasia = ?1 ORDER BY p.rodada.numero ASC")
    List<PontuacaoTimeFantasia> findByTimeFantasiaOrderByRodadaNumeroAsc(TimeFantasia timeFantasia);
}
