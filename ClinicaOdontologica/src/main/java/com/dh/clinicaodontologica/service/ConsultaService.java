package com.dh.clinicaodontologica.service;


import com.dh.clinicaodontologica.entity.Consulta;
import com.dh.clinicaodontologica.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;

//    public ConsultaService(ConsultaDAOH2 consultaDAOH2) {
//        this.consultaDAOH2 = consultaDAOH2;
//    }

    public Consulta salvar(Consulta consulta)  {
        return repository.save(consulta);
    }

    public List<Consulta> buscarTodos() {
        return repository.findAll();
    }

//    public List<Consulta> buscarPorPaciente(int id) throws SQLException {
//        return consultaDAOH2.buscarPorPaciente(id);
//    }
    public List<Consulta> buscarPorDentista(String nome) {
        return repository.findByDentistaNome(nome);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Consulta alterar(Consulta consulta) {
        repository.save(consulta);
        return consulta;
    }


    public Optional<Consulta> buscarPorId(long id) {
        return repository.findById(id);
    }
}
