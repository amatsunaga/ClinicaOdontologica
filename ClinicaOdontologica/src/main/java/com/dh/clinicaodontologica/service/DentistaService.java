package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.dao.impl.DentistaDAOH2;
import com.dh.clinicaodontologica.service.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DentistaService {
    @Autowired
    private DentistaDAOH2 dentistaDAOH2;

    public DentistaService(DentistaDAOH2 dentistaDAOH2) {
        this.dentistaDAOH2 = dentistaDAOH2;
    }

    public Dentista salvar(Dentista dentista) throws SQLException {
        return dentistaDAOH2.salvar(dentista);
    }

    public List<Dentista> buscarTodos() throws SQLException {
        return dentistaDAOH2.buscarTodos();
    }

    public void excluir(int id) throws SQLException {
        dentistaDAOH2.excluir(id);
    }

    public void alterar(Dentista dentista) throws SQLException {
        dentistaDAOH2.alterar(dentista);
    }
}
