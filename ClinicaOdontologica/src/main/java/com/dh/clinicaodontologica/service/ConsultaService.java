package com.dh.clinicaodontologica.service;


import com.dh.clinicaodontologica.entity.Consulta;
import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.dto.ConsultaDto;
import com.dh.clinicaodontologica.entity.dto.DentistaDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.repository.ConsultaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;

    public Consulta salvar(Consulta consulta) throws ResourceNotFoundException {
        try {
            return repository.save(consulta);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao cadastrar consulta: dados inseridos incorretamente ou consulta já existente.");
        }
    }

    public List<ConsultaDto> buscarTodos() throws EmptyListException {
        List<Consulta> consultaList = repository.findAll();

        if (consultaList.isEmpty()) throw new EmptyListException("Erro: não há consultas cadastradas.");

        List<ConsultaDto> consultaDtoList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        for (Consulta c : consultaList) {
            consultaDtoList.add(mapper.convertValue(c, ConsultaDto.class));
        }

        return consultaDtoList;
    }

    public ConsultaDto buscarPorId(Long id) throws ResourceNotFoundException {

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        ConsultaDto consultaDto = null;

        try {
            Consulta consultaOptional = repository.findById(id).get();
            consultaDto = mapper.convertValue(consultaOptional, ConsultaDto.class);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao buscar consulta: ID informado não existe.");
        }
        return consultaDto;
    }

    public List<ConsultaDto> buscarPorDentista(String nome) throws EmptyListException {
        List<Consulta> consultaList = repository.findByDentistaNome(nome);

        if (consultaList.isEmpty()) throw new EmptyListException("Erro: não há consultas cadastradas para este(a) dentista.");

        List<ConsultaDto> consultaDtoList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        for (Consulta c : consultaList) {
            consultaDtoList.add(mapper.convertValue(c, ConsultaDto.class));
        }

        return consultaDtoList;
    }

    public List<ConsultaDto> buscarPorPaciente(String nome) throws EmptyListException {
        List<Consulta> consultaList = repository.findByPacienteNome(nome);

        if (consultaList.isEmpty()) throw new EmptyListException("Erro: não há consultas cadastradas para este(a) paciente.");

        List<ConsultaDto> consultaDtoList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        for (Consulta c : consultaList) {
            consultaDtoList.add(mapper.convertValue(c, ConsultaDto.class));
        }

        return consultaDtoList;
    }

//    public List<ConsultaDto> buscarPorData(String data) throws EmptyListException {
//        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("YYYY-MM-DD").toFormatter();
//        //DateTimeFormatter dateTimeFormat = new DateTimeFormatter();
//        LocalDate dataConsulta = dateTimeFormatter.parse(data, LocalDate::from);
//        List<Consulta> consultaList = repository.findByData(String.valueOf(dataConsulta));
//
//        if (consultaList.isEmpty()) throw new EmptyListException("Erro: não há consultas cadastradas nesta data.");
//
//        List<ConsultaDto> consultaDtoList = new ArrayList<>();
//        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//
//        for (Consulta c : consultaList) {
//            consultaDtoList.add(mapper.convertValue(c, ConsultaDto.class));
//        }
//
//        return consultaDtoList;
//    }

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir consulta: ID informado não existe."));
        repository.deleteById(id);
    }

    public Consulta alterar(Consulta consulta) throws ResourceNotFoundException {
        try {
            Consulta consultaAAlterar = repository.findById(consulta.getConsultaId()).get();
            if (consulta.getDentista() != null) {
                consultaAAlterar.setDentista(consulta.getDentista());
            }
            if (consulta.getPaciente() != null) {
                consultaAAlterar.setPaciente(consulta.getPaciente());
            }
            if (consulta.getDataConsulta() != null) {
                consultaAAlterar.setDataConsulta(consulta.getDataConsulta());
            }
            if (consulta.getHorarioConsulta() != null) {
                consultaAAlterar.setHorarioConsulta(consulta.getHorarioConsulta());
            }
            return repository.save(consultaAAlterar);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao alterar consulta: ID informado não existe.");
        }
    }
}



