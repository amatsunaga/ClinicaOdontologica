package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Endereco;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity salvarEndereco(@RequestBody Endereco endereco) throws ResourceNotFoundException {
        return new ResponseEntity(service.salvar(endereco), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodos() throws EmptyListException {
        return new ResponseEntity(service.buscarTodos(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findEndereco/{idEndereco}", method = RequestMethod.GET)
    public ResponseEntity getEnderecoById(@PathVariable Long idEndereco) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscarPorId(idEndereco), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("idEndereco") Long idEndereco) throws ResourceNotFoundException {
        service.excluir(idEndereco);
        return new ResponseEntity("Endereço excluído com sucesso.", HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity alterar (@RequestBody Endereco endereco) throws ResourceNotFoundException {
        return new ResponseEntity(service.alterar(endereco), HttpStatus.OK);
    }
}
