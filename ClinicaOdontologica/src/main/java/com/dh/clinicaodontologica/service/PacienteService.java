package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.dao.impl.PacienteDAOH2;
import com.dh.clinicaodontologica.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteDAOH2 pacienteDao;

    public PacienteService(PacienteDAOH2 pacienteDao) {
        this.pacienteDao = pacienteDao;
    }

    public Paciente salvar(Paciente paciente) throws SQLException {
        return pacienteDao.salvar(paciente);
    }

    public void alterar(Paciente paciente) throws SQLException {
        pacienteDao.alterar(paciente);
    }

    public void excluir(int id) throws SQLException {
        pacienteDao.excluir(id);
    }

    public List<Paciente> buscarTodos() throws SQLException {
        return pacienteDao.buscarTodos();
    }
}
