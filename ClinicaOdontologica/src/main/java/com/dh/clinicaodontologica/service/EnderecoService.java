package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Endereco;
import com.dh.clinicaodontologica.entity.dto.EnderecoDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public Endereco salvar(Endereco endereco) throws ResourceNotFoundException {
        try {
            return repository.save(endereco);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao cadastrar endereço: rua, número e cidade não podem ser nulos.");
        }
    }

    public List<EnderecoDto> buscarTodos() throws EmptyListException {

            List<Endereco> enderecoList = repository.findAll();
            if (enderecoList.isEmpty()) throw new EmptyListException("Erro: não há endereços cadastrados.");

            List<EnderecoDto> enderecoDtoList = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();

            for (Endereco e : enderecoList) {
                enderecoDtoList.add(mapper.convertValue(e, EnderecoDto.class));
            }
            return enderecoDtoList;
        }

    public EnderecoDto buscarPorId(Long id) throws ResourceNotFoundException {

        ObjectMapper mapper = new ObjectMapper();
        EnderecoDto enderecoDto = null;

        try {
            Endereco enderecoOptional = repository.findById(id).get();
            enderecoDto = mapper.convertValue(enderecoOptional, EnderecoDto.class);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao buscar endereço: ID informado não existe.");
        }
        return enderecoDto;
    }
    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir endereço: ID informado não existe."));
        repository.deleteById(id);
    }
    public Endereco alterar(Endereco endereco) throws ResourceNotFoundException {
        try {
            Endereco enderecoAAlterar = repository.findById(endereco.getId()).get();
            if (endereco.getRua() != null) {
                enderecoAAlterar.setRua(endereco.getRua());
            }
            if (endereco.getNumero() != null) {
                enderecoAAlterar.setNumero(endereco.getNumero());
            }
            if (endereco.getCidade() != null) {
                enderecoAAlterar.setCidade(endereco.getCidade());
            }
            if (endereco.getBairro() != null) {
                enderecoAAlterar.setBairro(endereco.getBairro());
            }
            if (endereco.getCep() != null) {
                enderecoAAlterar.setCep(endereco.getCep());
            }
            return repository.save(enderecoAAlterar);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erro ao alterar endereço: ID informado não existe.");
        }
    }
}
