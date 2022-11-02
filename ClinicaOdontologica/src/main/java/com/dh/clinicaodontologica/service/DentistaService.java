package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.dto.DentistaRequestDTO;
import com.dh.clinicaodontologica.entity.dto.DentistaResponseDTO;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistaService {
    @Autowired
    DentistaRepository repository;
    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    Logger logger = Logger.getLogger(DentistaService.class);

    public ResponseEntity salvar(DentistaRequestDTO dentistaRequestDTO) throws ResourceNotFoundException {
        Dentista dentistaSalvo = null;
        Dentista dentista = mapper.convertValue(dentistaRequestDTO, Dentista.class);
        logger.info("Salvando dentista...");
        try {
            dentista.setMatricula();
            dentista.encodePassword();
            dentistaSalvo = repository.save(dentista);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao cadastrar dentista: dados inseridos incorretamente ou matrícula já existente.");
        }
        logger.info("Dentista salvo com sucesso.");
        return new ResponseEntity(mapper.convertValue(dentistaSalvo, DentistaResponseDTO.class), HttpStatus.OK);

    }

    public ResponseEntity buscarTodos() throws EmptyListException {
        logger.info("Buscando todos dentistas...");

        List<Dentista> dentistaList = repository.findAll();

        if (dentistaList.isEmpty()) throw new EmptyListException("Erro: não há dentistas cadastrados.");

        List<DentistaResponseDTO> dentistaResponseDTOList = new ArrayList<>();

        for (Dentista dentista : dentistaList) {
            dentistaResponseDTOList.add(mapper.convertValue(dentista, DentistaResponseDTO.class));
        }

        return new ResponseEntity(dentistaResponseDTOList, HttpStatus.OK);
    }

    public ResponseEntity buscarPorMatricula(String matricula) throws ResourceNotFoundException {
        logger.info("Buscando dentista de matrícula " + matricula + "...");

        Dentista dentista = null;

        try {
            dentista = repository.findByMatricula(matricula).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao buscar dentista: matrícula informada não existe.");
        }
        return new ResponseEntity(mapper.convertValue(dentista, DentistaResponseDTO.class), HttpStatus.OK);
    }

    public ResponseEntity excluir(String matricula) throws ResourceNotFoundException {
        logger.info("Excluindo dentista de matrícula " + matricula + "...");

        try {
            Dentista dentista = repository.findByMatricula(matricula).get();
            repository.delete(dentista);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao excluir dentista: ID informado não existe.");
        }
        return new ResponseEntity("Dentista excluído com sucesso.", HttpStatus.OK);
    }

    public ResponseEntity alterar(DentistaResponseDTO dentistaResponseDTO) throws ResourceNotFoundException {
        logger.info("Alterando dentista...");
        try {
            Dentista dentistaAAlterar = repository.findByMatricula(dentistaResponseDTO.getMatricula()).get();
            if (dentistaResponseDTO.getNome() != null) {
                dentistaAAlterar.setNome(dentistaResponseDTO.getNome());
            }
            if (dentistaResponseDTO.getSobrenome() != null) {
                dentistaAAlterar.setSobrenome(dentistaResponseDTO.getSobrenome());
            }
            if (dentistaResponseDTO.getUsuario().getUsername() != null) {
                dentistaAAlterar.getUsuario().setUsername(dentistaResponseDTO.getUsuario().getUsername());
            }
            return new ResponseEntity(mapper.convertValue(repository.save(dentistaAAlterar), DentistaResponseDTO.class), HttpStatus.OK);

        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao alterar dentista: " + ex.getMessage());
        }
    }

}
