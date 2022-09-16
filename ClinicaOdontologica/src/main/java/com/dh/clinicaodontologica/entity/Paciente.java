package com.dh.clinicaodontologica.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Paciente {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String rg;
    private LocalDate dataCadastro;


}