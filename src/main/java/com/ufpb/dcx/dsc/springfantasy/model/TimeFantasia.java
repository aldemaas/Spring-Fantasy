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
@Table(name = "times_fantasia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeFantasia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Nome do time é obrigatório")
    private String nome;

    @Column(nullable = false, precision = 15, scale = 2)
    @NotNull(message = "Patrimônio é obrigatório")
    @DecimalMin(value = "0.0", message = "Patrimônio não pode ser negativo")
    private BigDecimal patrimonio = new BigDecimal("100.0");

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    @NotNull(message = "Usuário é obrigatório")
    private Usuario usuario;

    @OneToMany(mappedBy = "timeFantasia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Escalacao> escalacoes;
}
