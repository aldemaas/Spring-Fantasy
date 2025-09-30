package com.ufpb.dcx.dsc.springfantasy.repository;

import com.ufpb.dcx.dsc.springfantasy.model.Escalacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EscalacaoRepository extends JpaRepository<Escalacao, Long> {

    List<Escalacao> findByRodadaId(Long rodadaId);

    List<Escalacao> findByRodada(com.ufpb.dcx.dsc.springfantasy.model.Rodada rodada);

    @Query("SELECT e FROM Escalacao e WHERE e.timeFantasia.id = :timeFantasiaId AND e.rodada.id = :rodadaId")
    List<Escalacao> findByTimeFantasiaAndRodada(@Param("timeFantasiaId") Long timeFantasiaId, @Param("rodadaId") Long rodadaId);

    @Query("SELECT e FROM Escalacao e WHERE e.timeFantasia.usuario.id = :usuarioId AND e.rodada.id = :rodadaId")
    List<Escalacao> findByUsuarioAndRodada(@Param("usuarioId") Long usuarioId, @Param("rodadaId") Long rodadaId);

    @Query("SELECT COUNT(e) FROM Escalacao e WHERE e.timeFantasia.id = :timeFantasiaId AND e.rodada.id = :rodadaId")
    long countByTimeFantasiaAndRodada(@Param("timeFantasiaId") Long timeFantasiaId, @Param("rodadaId") Long rodadaId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Escalacao e WHERE e.timeFantasia.id = :timeFantasiaId AND e.rodada.id = :rodadaId")
    void deleteByTimeFantasiaAndRodada(@Param("timeFantasiaId") Long timeFantasiaId, @Param("rodadaId") Long rodadaId);
}
