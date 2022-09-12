package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.model.Endereco;
import com.dh.clinicaodontologica.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public Endereco salvar(Endereco endereco)  {
        return repository.save(endereco);
    }
}
