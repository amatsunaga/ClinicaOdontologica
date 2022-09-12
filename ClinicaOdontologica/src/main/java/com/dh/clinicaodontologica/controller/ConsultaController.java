package com.dh.clinicaodontologica.controller;


import com.dh.clinicaodontologica.model.Consulta;
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
    public Consulta salvarConsulta(@RequestBody Consulta consulta){
        return service.salvar(consulta);
    }

    @GetMapping
    public List<Consulta> buscarTodos(){
        return service.buscarTodos();
    }


    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) {
        service.excluir(id);
    }

    @PatchMapping
    public void alterar(@RequestBody Consulta consulta) {
        service.alterar(consulta);
    }
}
