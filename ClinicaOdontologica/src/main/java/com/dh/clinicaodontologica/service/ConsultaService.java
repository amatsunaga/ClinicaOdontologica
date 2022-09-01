package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.dao.impl.ConsultaDAOH2;
import com.dh.clinicaodontologica.service.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaDAOH2 consultaDAOH2;

    public ConsultaService(ConsultaDAOH2 consultaDAOH2) {
        this.consultaDAOH2 = consultaDAOH2;
    }

    public Consulta salvar(Consulta consulta) throws SQLException {
        return consultaDAOH2.salvar(consulta);
    }

    public List<Consulta> buscarTodos() throws SQLException {
        return consultaDAOH2.buscarTodos();
    }

    public void excluir(int id) throws SQLException {
        consultaDAOH2.excluir(id);
    }

    public void alterar(Consulta consulta) throws SQLException {
        consultaDAOH2.alterar(consulta);
    }


}
