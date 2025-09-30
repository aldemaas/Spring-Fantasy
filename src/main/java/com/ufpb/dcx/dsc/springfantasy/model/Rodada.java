package com.ufpb.dcx.dsc.springfantasy.model;

import com.ufpb.dcx.dsc.springfantasy.enums.RodadaStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rodadas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rodada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Número da rodada é obrigatório")
    @Min(value = 1, message = "Número da rodada deve ser maior que zero")
    private Integer numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RodadaStatus status = RodadaStatus.MERCADO_ABERTO;

    @OneToMany(mappedBy = "rodada", fetch = FetchType.LAZY)
    private List<PontuacaoJogador> pontuacoes;

    @OneToMany(mappedBy = "rodada", fetch = FetchType.LAZY)
    private List<Escalacao> escalacoes;
}
