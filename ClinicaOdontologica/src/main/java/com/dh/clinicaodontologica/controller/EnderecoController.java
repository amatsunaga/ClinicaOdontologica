package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Endereco;
import com.dh.clinicaodontologica.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

//    @RequestMapping(value = "/findEndereco/{idEndereco}", method = RequestMethod.GET)
//    public Optional<Endereco> getEnderecoById(@PathVariable Long idEndereco){
//        return service.buscarPorId(idEndereco);
//    }

    @PostMapping
    public Endereco salvarEndereco(@RequestBody Endereco endereco){
        return service.salvar(endereco);
    }
}
