package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.entity.dto.PacienteDto;
import com.dh.clinicaodontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    private Logger logger = Logger.getLogger(PacienteService.class);


    public Paciente salvar(Paciente paciente)  {
        return repository.save(paciente);
    }

    public List<PacienteDto> buscarTodos(){
        List<Paciente> listPaciente = repository.findAll();
        List<PacienteDto> listPacienteDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (Paciente p : listPaciente){
            listPacienteDTO.add(mapper.convertValue(p, PacienteDto.class));
        }

        return listPacienteDTO;
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
