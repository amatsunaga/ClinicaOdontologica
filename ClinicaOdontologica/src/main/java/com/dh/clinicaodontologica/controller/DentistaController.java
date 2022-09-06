package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.model.Dentista;
import com.dh.clinicaodontologica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaService service;

    @RequestMapping(value = "/findDentista/{idDentista}", method = RequestMethod.GET)
    public String getDentistaById(@PathVariable Long idDentista){
        return "Você pediu o dentista: "+idDentista ;
    }
    @PostMapping
    public Dentista salvarDentista(@RequestBody Dentista dentista) {
        return service.salvar(dentista);
    }

    @PatchMapping
    public void alterar(@RequestBody Dentista dentista) {
        service.alterar(dentista);
    }

    @GetMapping
    public List<Dentista> buscarTodos(){
        return service.buscarTodos();
    }
}
