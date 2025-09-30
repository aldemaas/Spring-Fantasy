package com.ufpb.dcx.dsc.springfantasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "jogadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Nome do jogador é obrigatório")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "Posição é obrigatória")
    private String posicao;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clube_id", nullable = false)
    @NotNull(message = "Clube é obrigatório")
    private Clube clube;

    @OneToMany(mappedBy = "jogador", fetch = FetchType.LAZY)
    private List<PontuacaoJogador> pontuacoes;

    @OneToMany(mappedBy = "jogador", fetch = FetchType.LAZY)
    private List<Escalacao> escalacoes;
}
