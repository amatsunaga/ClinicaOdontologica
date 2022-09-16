package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Endereco;
import com.dh.clinicaodontologica.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity salvarEndereco(@RequestBody Endereco endereco){
        Endereco enderecoSalvo = service.salvar(endereco);
        if(enderecoSalvo == null) {
            return new ResponseEntity("Erro ao salvar endereço", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(enderecoSalvo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> buscarTodos() {
        List<Endereco> enderecos = service.buscarTodos();
        if(enderecos.isEmpty()) {
            return new ResponseEntity("Nenhum endereço encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(enderecos, HttpStatus.OK);
    }

    @RequestMapping(value = "/findEndereco/{idEndereco}", method = RequestMethod.GET)
    public ResponseEntity getEnderecoById(@PathVariable Long idEndereco){
        Optional<Endereco> enderecoOptional = service.buscarPorId(idEndereco);
        if(enderecoOptional.isEmpty()) {
            return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(enderecoOptional, HttpStatus.OK);
    }

    @PatchMapping
    public void alterar(@RequestBody Endereco endereco) {
        service.alterar(endereco);
    }

    @DeleteMapping
    public void excluir(@RequestParam("idEndereco") Long idEndereco)  {
        service.excluir(idEndereco);
    }

}
