package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.dto.DentistaDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaService service;

    @PostMapping
    public ResponseEntity salvarDentista(@RequestBody Dentista dentista) throws ResourceNotFoundException {
        return new ResponseEntity(service.salvar(dentista), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity buscarTodos() throws EmptyListException {
        return new ResponseEntity(service.buscarTodos(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findDentista/{idDentista}", method = RequestMethod.GET)
    public ResponseEntity getDentistaById(@PathVariable Long idDentista) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscarPorId(idDentista), HttpStatus.OK);
    }

    @RequestMapping(value = "/findByMatricula/{matricula}", method = RequestMethod.GET)
    public ResponseEntity getDentistaByMatricula(@PathVariable String matricula) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscarPorMatricula(matricula), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("idDentista") Long idDentista) throws ResourceNotFoundException {
        service.excluir(idDentista);
        return new ResponseEntity("Dentista excluído com sucesso.", HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity alterar(@RequestBody Dentista dentista) throws ResourceNotFoundException {
        return new ResponseEntity(service.alterar(dentista), HttpStatus.OK);
    }

}
