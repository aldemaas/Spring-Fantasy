package com.ufpb.dcx.dsc.springfantasy.repository;

import com.ufpb.dcx.dsc.springfantasy.enums.RodadaStatus;
import com.ufpb.dcx.dsc.springfantasy.model.Rodada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RodadaRepository extends JpaRepository<Rodada, Long> {

    Optional<Rodada> findFirstByOrderByNumeroDesc();

    Optional<Rodada> findByNumero(Integer numero);

    Optional<Rodada> findFirstByStatusOrderByNumeroDesc(RodadaStatus status);
}
