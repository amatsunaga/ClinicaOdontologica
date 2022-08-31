package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.dao.impl.PacienteDAOH2;
import com.dh.clinicaodontologica.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

class PacienteServiceTest {

    PacienteService pacienteService;

    @BeforeEach
    void doBefore() {
        pacienteService = new PacienteService(new PacienteDAOH2());

    }

    @Test
    public void cadastroPaciente() throws SQLException {
        Paciente paciente = new Paciente(
                1, "˜Will", "Almeida", 1, "395.237.378-92",LocalDate.now());
        pacienteService.salvar(paciente);
        Assertions.assertTrue(paciente.getId() >= 1);

        Paciente paciente2 = new Paciente(
                2, "João", "Silva", 2, "395.367.378-87", LocalDate.now());
        pacienteService.salvar(paciente2);
        Assertions.assertTrue(paciente2.getId() >= 2);

    }

}