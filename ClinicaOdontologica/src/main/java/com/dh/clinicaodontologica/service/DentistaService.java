package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    private DentistaRepository repository;

    public Dentista salvar(Dentista dentista)  {
        return repository.save(dentista);
    }


    public List<Dentista> buscarTodos() {
        return repository.findAll();
    }

    public Optional<Dentista> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Dentista alterar(Dentista dentista) {
        return repository.save(dentista);
    }

}
