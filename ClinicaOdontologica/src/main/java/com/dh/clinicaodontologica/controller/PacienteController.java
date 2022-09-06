package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @RequestMapping(value = "/findPaciente/{idPaciente}", method = RequestMethod.GET)
        public Optional<Paciente> getPacienteById(@PathVariable Long idPaciente){
            return service.buscarPorId(idPaciente);
        }

    @PostMapping
    public Paciente salvarPaciente(@RequestBody Paciente paciente){
        return service.salvar(paciente);
    }

    @PatchMapping
    public void alterar(@RequestBody Paciente paciente) {
        service.alterar(paciente);
    }

    @DeleteMapping
    public void excluir(@RequestParam("idPaciente") Long idPaciente)  {
        service.excluir(idPaciente);
    }

    @GetMapping
    public List<Paciente> buscarTodos() {
        return service.buscarTodos();
    }
}
