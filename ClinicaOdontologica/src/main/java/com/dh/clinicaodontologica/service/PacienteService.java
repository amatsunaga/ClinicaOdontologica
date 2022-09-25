package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.entity.dto.DentistaDto;
import com.dh.clinicaodontologica.entity.dto.PacienteDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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


    public Paciente salvar(Paciente paciente) throws ResourceNotFoundException {
        try {
            return repository.save(paciente);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao cadastrar paciente: nome, sobrenome e RG são obrigatórios. Verifique também se o ID do endereço está correto.");
        }
    }

    public List<PacienteDto> buscarTodos() throws EmptyListException {
        List<Paciente> listPaciente = repository.findAll();

        if (listPaciente.isEmpty()) throw new EmptyListException("Erro: não há pacientes cadastrados.");

        List<PacienteDto> listPacienteDTO = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        for (Paciente p : listPaciente){
            listPacienteDTO.add(mapper.convertValue(p, PacienteDto.class));
        }

        return listPacienteDTO;
    }

    public PacienteDto buscarPorId(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        PacienteDto pacienteDto = null;

        try {
            Paciente pacienteOptional = repository.findById(id).get();
            pacienteDto = mapper.convertValue(pacienteOptional, PacienteDto.class);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao buscar paciente: ID informado não existe.");
        }
        return pacienteDto;
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir paciente: ID informado não existe."));
        repository.deleteById(id);
    }

    public Paciente alterar(Paciente paciente) throws ResourceNotFoundException {

        try {
            Paciente pacienteAAlterar = repository.findById(paciente.getId()).get();
            if (paciente.getNome() != null) {
                pacienteAAlterar.setNome(paciente.getNome());
            }
            if (paciente.getSobrenome() != null) {
                pacienteAAlterar.setSobrenome(paciente.getSobrenome());
            }
            if (paciente.getEndereco() != null) {
                pacienteAAlterar.setEndereco(paciente.getEndereco());
            }
            if (paciente.getRg() != null) {
                pacienteAAlterar.setRg(paciente.getRg());
            }
            if (paciente.getDataCadastro() != null) {
                pacienteAAlterar.setDataCadastro(paciente.getDataCadastro());
            }
            return repository.save(pacienteAAlterar);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao alterar paciente: ID informado não existe.");
        }
    }
}
