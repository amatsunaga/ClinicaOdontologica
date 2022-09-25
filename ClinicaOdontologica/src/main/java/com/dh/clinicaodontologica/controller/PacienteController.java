package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.entity.dto.PacienteDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
@CrossOrigin( "*" )
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity salvarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        return new ResponseEntity(service.salvar(paciente), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() throws EmptyListException {
        return new ResponseEntity(service.buscarTodos(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findPaciente/{idPaciente}", method = RequestMethod.GET)
    public ResponseEntity getPacienteById(@PathVariable Long idPaciente) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscarPorId(idPaciente), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("idPaciente") Long idPaciente) throws ResourceNotFoundException  {
        service.excluir(idPaciente);
        return new ResponseEntity("Paciente excluído com sucesso.", HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity alterar(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        return new ResponseEntity(service.alterar(paciente), HttpStatus.OK);
    }

}
