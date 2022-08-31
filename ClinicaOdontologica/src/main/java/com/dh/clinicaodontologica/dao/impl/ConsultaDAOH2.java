package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.model.Consulta;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.List;

@Configuration
public class ConsultaDAOH2 implements IDao<Consulta> {
    public ConsultaDAOH2() {
    }

    @Override
    public Consulta salvar(Consulta consulta) throws SQLException {
        return null;
    }

    @Override
    public List<Consulta> buscarTodos() throws SQLException {
        return null;
    }

    @Override
    public void excluir(int id) throws SQLException {

    }

    @Override
    public void alterar(Consulta consulta) throws SQLException {

    }
}
