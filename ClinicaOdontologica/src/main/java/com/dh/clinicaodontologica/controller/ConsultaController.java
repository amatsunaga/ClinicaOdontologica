package com.dh.clinicaodontologica.controller;


import com.dh.clinicaodontologica.service.model.Consulta;
import com.dh.clinicaodontologica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @PostMapping
    public Consulta salvarConsulta(@RequestBody Consulta consulta) throws SQLException {
        return service.salvar(consulta);
    }

    @GetMapping
    public List<Consulta> buscarTodos() throws SQLException {
        return service.buscarTodos();
    }
}
