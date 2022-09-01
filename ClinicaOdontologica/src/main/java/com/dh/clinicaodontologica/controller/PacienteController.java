package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @RequestMapping(value = "/findPaciente/{idPaciente}", method = RequestMethod.GET)
        public Paciente getPacienteById(@PathVariable int idPaciente) throws SQLException {
            return service.buscarPorId(idPaciente);
        }

    @PostMapping
    public Paciente salvarPaciente(@RequestBody Paciente paciente) throws SQLException {
        return service.salvar(paciente);
    }

    @PatchMapping
    public void alterar(@RequestBody Paciente paciente) throws SQLException {
        System.out.println();
        service.alterar(paciente);
    }

    @DeleteMapping
    public void excluir(@RequestParam("idPaciente") int idPaciente) throws SQLException {
        service.excluir(idPaciente);
    }

    @GetMapping
    public List<Paciente> buscarTodos() throws SQLException {
        return service.buscarTodos();
    }
}
