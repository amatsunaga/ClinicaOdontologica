package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Endereco;
import com.dh.clinicaodontologica.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public Endereco salvar(Endereco endereco)  {
        return repository.save(endereco);
    }

    public List<Endereco> buscarTodos() {
        return repository.findAll();
    }
    public Optional<Endereco> buscarPorId(Long id) {
        return repository.findById(id);
    }
    public void excluir(Long id) {
        repository.deleteById(id);
    }
    public void alterar(Endereco endereco) {
        repository.save(endereco);
    }
}
