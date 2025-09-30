package com.ufpb.dcx.dsc.springfantasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.ufpb.dcx.dsc.springfantasy.model.Jogador;

@Entity
@Table(name = "clubes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Nome do clube é obrigatório")
    private String nome;

    @OneToMany(mappedBy = "clube", fetch = FetchType.LAZY)
    private List<Jogador> jogadores;
}
