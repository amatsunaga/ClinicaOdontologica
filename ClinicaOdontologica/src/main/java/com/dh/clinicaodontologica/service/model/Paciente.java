package com.dh.clinicaodontologica.service.model;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {

    private int id;
    private String nome;
    private String sobrenome;
    private int enderecoId;
    private String rg;
    private LocalDate dataCadastro;


}