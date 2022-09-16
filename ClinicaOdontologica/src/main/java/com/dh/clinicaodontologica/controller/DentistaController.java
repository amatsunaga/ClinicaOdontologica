package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaService service;

    @PostMapping
    public ResponseEntity salvarDentista(@RequestBody Dentista dentista) {
        Dentista dentistaSalvo = service.salvar(dentista);
        if(dentistaSalvo == null) {
            return new ResponseEntity("Erro ao salvar dentista", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(dentistaSalvo, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity buscarTodos(){
        List<Dentista> dentistas = service.buscarTodos();
        if(dentistas.isEmpty()) {
            return new ResponseEntity("Nenhum dentista encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dentistas, HttpStatus.OK);
    }

    @RequestMapping(value = "/findDentista/{idDentista}", method = RequestMethod.GET)
    public ResponseEntity getDentistaById(@PathVariable Long idDentista){
        Optional<Dentista> dentistaOptional = service.buscarPorId(idDentista);
        if(dentistaOptional.isEmpty()) {
            return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dentistaOptional, HttpStatus.OK);
    }

    @PatchMapping
    public Dentista alterar(@RequestBody Dentista dentista) {
        return service.alterar(dentista);
    }

    @DeleteMapping
    public void excluir(@RequestParam("idDentista") Long idDentista)  {
        service.excluir(idDentista);
    }

}
