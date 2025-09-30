package com.ufpb.dcx.dsc.springfantasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pontuacoes_times_fantasia",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"time_fantasia_id", "rodada_id"})
       })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontuacaoTimeFantasia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_fantasia_id", nullable = false)
    @NotNull(message = "Time Fantasia é obrigatório")
    private TimeFantasia timeFantasia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rodada_id", nullable = false)
    @NotNull(message = "Rodada é obrigatória")
    private Rodada rodada;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Pontos são obrigatórios")
    private BigDecimal pontos;
}
