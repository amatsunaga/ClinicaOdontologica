package com.dh.clinicaodontologica.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    public T salvar(T t) throws SQLException;
    public List<T> buscarTodos();
}
