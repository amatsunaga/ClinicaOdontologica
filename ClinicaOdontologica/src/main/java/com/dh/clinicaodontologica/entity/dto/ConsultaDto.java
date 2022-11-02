package com.dh.clinicaodontologica.entity.dto;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.Endereco;
import com.dh.clinicaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaDto {

    @NotBlank
    private Dentista dentista;

    @NotBlank
    private Paciente paciente;

    @NotBlank
    private LocalDateTime dataHoraConsulta;

}
