package com.ufpb.dcx.dsc.springfantasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pontuacoes_jogadores",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"jogador_id", "rodada_id"})
       })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontuacaoJogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 5, scale = 2)
    @NotNull(message = "Pontos são obrigatórios")
    @DecimalMin(value = "0.0", message = "Pontos não podem ser negativos")
    private BigDecimal pontos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id", nullable = false)
    @NotNull(message = "Jogador é obrigatório")
    private Jogador jogador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rodada_id", nullable = false)
    @NotNull(message = "Rodada é obrigatória")
    private Rodada rodada;
}
