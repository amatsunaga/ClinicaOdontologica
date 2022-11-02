package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.dto.DentistaRequestDTO;
import com.dh.clinicaodontologica.entity.dto.DentistaResponseDTO;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaService dentistaService;

    @PostMapping
    public ResponseEntity salvarDentista(@RequestBody DentistaRequestDTO dentista) throws ResourceNotFoundException {
        return dentistaService.salvar(dentista);
    }
    @GetMapping
    public ResponseEntity buscarTodos() throws EmptyListException {
        return dentistaService.buscarTodos();
    }

    @RequestMapping(value = "/findByMatricula/{matricula}", method = RequestMethod.GET)
    public ResponseEntity getDentistaByMatricula(@PathVariable String matricula) throws ResourceNotFoundException {
        return dentistaService.buscarPorMatricula(matricula);
    }

    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("matricula") String matricula) throws ResourceNotFoundException {
        return dentistaService.excluir(matricula);
    }

    @PatchMapping
    public ResponseEntity alterar(@RequestBody DentistaResponseDTO dentista) throws ResourceNotFoundException {
        return dentistaService.alterar(dentista);
    }

}
