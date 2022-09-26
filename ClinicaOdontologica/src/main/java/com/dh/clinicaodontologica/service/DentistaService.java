package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Consulta;
import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.dto.ConsultaDto;
import com.dh.clinicaodontologica.entity.dto.DentistaDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    private DentistaRepository repository;

    private Logger logger = Logger.getLogger(DentistaService.class);

    public Dentista salvar(Dentista dentista) throws ResourceNotFoundException {
        logger.info("Salvando dentista...");
        try {
            logger.info("Dentista salvo com sucesso.");
            return repository.save(dentista);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao cadastrar dentista: dados inseridos incorretamente ou matrícula já existente.");
        }
    }

    public List<DentistaDto> buscarTodos() throws EmptyListException {
        logger.info("Buscando todos dentistas...");

        List<Dentista> dentistaList = repository.findAll();

        if (dentistaList.isEmpty()) throw new EmptyListException("Erro: não há dentistas cadastrados.");

        List<DentistaDto> dentistaDtoList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        for (Dentista d : dentistaList) {
            dentistaDtoList.add(mapper.convertValue(d, DentistaDto.class));
        }

        return dentistaDtoList;
    }

    public DentistaDto buscarPorId(Long id) throws ResourceNotFoundException {
        logger.info("Buscando dentista de ID " + id + "...");

        ObjectMapper mapper = new ObjectMapper();
        DentistaDto dentistaDto = null;

        try {
            Dentista dentistaOptional = repository.findById(id).get();
            dentistaDto = mapper.convertValue(dentistaOptional, DentistaDto.class);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao buscar dentista: ID informado não existe.");
        }
        return dentistaDto;
    }

    public DentistaDto buscarPorMatricula(String matricula) throws ResourceNotFoundException {
        logger.info("Buscando dentista de matrícula " + matricula + "...");

        ObjectMapper mapper = new ObjectMapper();
        DentistaDto dentistaDto = null;

        try {
            Dentista dentistaOptional = repository.findByMatricula(matricula).get();
            dentistaDto = mapper.convertValue(dentistaOptional, DentistaDto.class);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao buscar dentista: matrícula informada não existe.");
        }
        return dentistaDto;
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        logger.info("Excluindo dentista de ID " + id + "...");
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir dentista: ID informado não existe."));
        repository.deleteById(id);
    }

    public Dentista alterar(Dentista dentista) throws ResourceNotFoundException {
        logger.info("Alterando dentista...");
        try {
            Dentista dentistaAAlterar = repository.findById(dentista.getId()).get();
            if (dentista.getNome() != null) {
                dentistaAAlterar.setNome(dentista.getNome());
            }
            if (dentista.getSobrenome() != null) {
                dentistaAAlterar.setSobrenome(dentista.getSobrenome());
            }
            if (dentista.getMatricula() != null) {
                dentistaAAlterar.setMatricula(dentista.getMatricula());
            }
            return repository.save(dentistaAAlterar);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao alterar dentista: ID informado não existe.");
        }
    }

}
