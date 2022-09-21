package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.entity.dto.PacienteDto;
import com.dh.clinicaodontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
@CrossOrigin( "*" )
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity salvarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteSalvo = service.salvar(paciente);
        if(pacienteSalvo == null) {
            return new ResponseEntity("Erro ao salvar paciente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(pacienteSalvo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() {
        List<PacienteDto> pacientes = service.buscarTodos();
        if(pacientes.isEmpty()){
            return new ResponseEntity("Nenhum Paciente Encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(pacientes, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findPaciente/{idPaciente}", method = RequestMethod.GET)
    public ResponseEntity getPacienteById(@PathVariable Long idPaciente){
        Optional<Paciente> pacienteOptional = service.buscarPorId(idPaciente);
        if(pacienteOptional.isEmpty()) {
            return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(pacienteOptional, HttpStatus.OK);
    }

    @PatchMapping
    public void alterar(@RequestBody Paciente paciente) {
        service.alterar(paciente);
    }

    @DeleteMapping
    public void excluir(@RequestParam("idPaciente") Long idPaciente)  {
        service.excluir(idPaciente);
    }
}
