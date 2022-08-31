package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.service.model.Dentista;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.List;

@Configuration
public class DentistaDAOH2 implements IDao<Dentista> {
    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {
        return null;
    }

    @Override
    public List<Dentista> buscarTodos() throws SQLException {
        return null;
    }

    @Override
    public void excluir(int id) throws SQLException {

    }

    @Override
    public void alterar(Dentista dentista) throws SQLException {

    }
}
