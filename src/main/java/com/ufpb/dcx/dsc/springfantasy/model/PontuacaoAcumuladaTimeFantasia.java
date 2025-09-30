package com.ufpb.dcx.dsc.springfantasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pontuacao_acumulada_time_fantasia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontuacaoAcumuladaTimeFantasia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_fantasia_id", nullable = false, unique = true)
    @NotNull(message = "Time Fantasia é obrigatório")
    private TimeFantasia timeFantasia;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Pontuação total é obrigatória")
    private BigDecimal pontuacaoTotal = BigDecimal.ZERO;

    @Column(nullable = false)
    @NotNull(message = "Rodada atual é obrigatória")
    private Integer rodadaAtual = 1;

    // Construtor de conveniência
    public PontuacaoAcumuladaTimeFantasia(TimeFantasia timeFantasia) {
        this.timeFantasia = timeFantasia;
        this.pontuacaoTotal = BigDecimal.ZERO;
        this.rodadaAtual = 1;
    }

    public void adicionarPontos(BigDecimal pontos) {
        this.pontuacaoTotal = this.pontuacaoTotal.add(pontos);
    }

    public void resetarPontuacao() {
        this.pontuacaoTotal = BigDecimal.ZERO;
        this.rodadaAtual = 1;
    }

    public void avancarRodada() {
        this.rodadaAtual++;
    }
}
