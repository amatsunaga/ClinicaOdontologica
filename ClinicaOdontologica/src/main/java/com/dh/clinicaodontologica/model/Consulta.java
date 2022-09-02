package com.dh.clinicaodontologica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    private int consultaId;
    private int dentistaId;
    private int pacienteId;
    private LocalDate dataConsulta;

    public Consulta(int dentistaId, int pacienteId, LocalDate dataConsulta) {
        this.dentistaId = dentistaId;
        this.pacienteId = pacienteId;
        this.dataConsulta = dataConsulta;
    }
}
