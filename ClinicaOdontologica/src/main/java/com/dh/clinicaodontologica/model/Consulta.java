package com.dh.clinicaodontologica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Consulta {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consulta_id")
    private Long consultaId;

    @Column(name = "dentista_id")
    private Long dentistaId;

    @Column(name = "paciente_id")
    private Long pacienteId;

    private LocalDate dataConsulta;

    public Consulta(Long dentistaId, Long pacienteId, LocalDate dataConsulta) {
        this.dentistaId = dentistaId;
        this.pacienteId = pacienteId;
        this.dataConsulta = dataConsulta;
    }
}
