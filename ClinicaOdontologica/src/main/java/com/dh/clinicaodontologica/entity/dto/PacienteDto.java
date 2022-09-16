package com.dh.clinicaodontologica.entity.dto;

import com.dh.clinicaodontologica.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {

    private String nome;
    private String sobrenome;
    private Endereco endereco;
    private String rg;
    private LocalDate dataCadastro;
}
