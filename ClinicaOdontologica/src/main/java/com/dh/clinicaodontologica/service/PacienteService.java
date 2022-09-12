package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.model.Paciente;
import com.dh.clinicaodontologica.repository.EnderecoRepository;
import com.dh.clinicaodontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;


    public Paciente salvar(Paciente paciente)  {
        return repository.save(paciente);
    }

    public List<Paciente> buscarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }


    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public void alterar(Paciente paciente) {
        repository.save(paciente);
    }
}
