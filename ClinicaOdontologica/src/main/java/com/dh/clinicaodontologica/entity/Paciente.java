package com.dh.clinicaodontologica.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String sobrenome;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @NotNull
    private String rg;

    private LocalDate dataCadastro;
}