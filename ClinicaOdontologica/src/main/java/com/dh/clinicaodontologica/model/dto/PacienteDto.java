package com.dh.clinicaodontologica.model.dto;

import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.*;
import java.util.List;

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
