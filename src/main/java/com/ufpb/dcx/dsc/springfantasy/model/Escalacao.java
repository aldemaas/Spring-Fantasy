package com.ufpb.dcx.dsc.springfantasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "escalacoes",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"time_fantasia_id", "jogador_id", "rodada_id"})
       })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Escalacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_fantasia_id", nullable = false)
    @NotNull(message = "Time fantasia é obrigatório")
    private TimeFantasia timeFantasia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id", nullable = false)
    @NotNull(message = "Jogador é obrigatório")
    private Jogador jogador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rodada_id", nullable = false)
    @NotNull(message = "Rodada é obrigatória")
    private Rodada rodada;
}
