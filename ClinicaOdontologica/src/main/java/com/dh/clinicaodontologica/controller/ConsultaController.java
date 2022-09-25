package com.dh.clinicaodontologica.controller;


import com.dh.clinicaodontologica.entity.Consulta;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @PostMapping
    public ResponseEntity salvarConsulta(@RequestBody Consulta consulta) throws ResourceNotFoundException {
        return new ResponseEntity(service.salvar(consulta), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodos() throws EmptyListException {
        return new ResponseEntity(service.buscarTodos(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findConsulta/{idConsulta}", method = RequestMethod.GET)
    public ResponseEntity getConsultaById(@PathVariable Long idConsulta) throws ResourceNotFoundException {
        return new ResponseEntity(service.buscarPorId(idConsulta), HttpStatus.OK);
    }

    @RequestMapping(value = "/findByDentista", method = RequestMethod.GET)
    public ResponseEntity getConsultaByDentista(@RequestParam("nome") String nome) throws EmptyListException {
        return new ResponseEntity(service.buscarPorDentista(nome), HttpStatus.OK);
    }

    @RequestMapping(value = "/findByPaciente", method = RequestMethod.GET)
    public ResponseEntity getConsultasByPaciente(@RequestParam("nome") String nome) throws EmptyListException {
        return new ResponseEntity(service.buscarPorPaciente(nome), HttpStatus.OK);
    }

//    @RequestMapping(value = "/findByData", method = RequestMethod.GET)
//    public ResponseEntity getConsultasByData(@RequestParam("data") String data) throws EmptyListException {
//        return new ResponseEntity(service.buscarPorData(data), HttpStatus.OK);
//    }

    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
        return new ResponseEntity("Consulta excluída com sucesso.", HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity alterar(@RequestBody Consulta consulta) throws ResourceNotFoundException {
        return new ResponseEntity(service.alterar(consulta), HttpStatus.OK);
    }
}
