package com.ufpb.dcx.dsc.springfantasy.repository;

import com.ufpb.dcx.dsc.springfantasy.model.TimeFantasia;
import com.ufpb.dcx.dsc.springfantasy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeFantasiaRepository extends JpaRepository<TimeFantasia, Long> {

    Optional<TimeFantasia> findByUsuario(Usuario usuario);

    @Query("SELECT tf FROM TimeFantasia tf WHERE tf.usuario.id = :usuarioId")
    Optional<TimeFantasia> findByUsuarioId(@Param("usuarioId") Long usuarioId);

    boolean existsByNome(String nome);

    @Query("SELECT tf FROM TimeFantasia tf ORDER BY tf.patrimonio DESC")
    List<TimeFantasia> findAllOrderByPatrimonioDesc();
}
