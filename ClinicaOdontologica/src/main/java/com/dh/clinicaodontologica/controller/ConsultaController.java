package com.dh.clinicaodontologica.controller;


import com.dh.clinicaodontologica.model.Consulta;
import com.dh.clinicaodontologica.model.Dentista;
import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @PostMapping
    public ResponseEntity salvarConsulta(@RequestBody Consulta consulta){
        Consulta consultaSalva = service.salvar(consulta);
        if(consultaSalva == null) {
            return new ResponseEntity("Erro ao salvar consulta", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(consultaSalva, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodos(){
        List<Consulta> consultas = service.buscarTodos();
        if(consultas.isEmpty()) {
            return new ResponseEntity("Nenhuma consulta encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(consultas, HttpStatus.OK);
    }

    @GetMapping(path = "/buscaPorDentista")
    public ResponseEntity buscarPorDentista(@RequestParam("nome") String nome) {
        List<Consulta> consultas = service.buscarPorDentista(nome);
        if(consultas.isEmpty()) {
            return new ResponseEntity("Nenhuma consulta agendada para este(a) dentista", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(consultas, HttpStatus.OK);
    }

    @PatchMapping
    public void alterar(@RequestBody Consulta consulta) {
        service.alterar(consulta);
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) {
        service.excluir(id);
    }


}
