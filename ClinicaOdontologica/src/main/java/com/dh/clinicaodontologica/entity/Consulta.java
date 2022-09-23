package com.dh.clinicaodontologica.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Consulta {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consulta_id")
    private Long consultaId;


    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private Dentista dentista;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime dataHoraConsulta;

}
