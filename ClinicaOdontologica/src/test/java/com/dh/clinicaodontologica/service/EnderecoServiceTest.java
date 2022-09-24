package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Consulta;
import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.Endereco;
import javax.transaction.Transactional;

import com.dh.clinicaodontologica.entity.dto.DentistaDto;
import com.dh.clinicaodontologica.entity.dto.EnderecoDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EnderecoServiceTest {

    @Autowired
    EnderecoService service;

    static Endereco endereco;
    static List<Endereco> enderecoList;

    @BeforeEach
    void doBefore() {
        this.endereco = new Endereco();
        this.endereco.setRua("Rua das Dores");
        this.endereco.setNumero("666");
        this.endereco.setCidade("Manaus");
        this.endereco.setBairro("Ubirajara");
        this.endereco.setCep("69015690");
    }

    @Test
    void concluindoSalvamento() throws ResourceNotFoundException {
        Endereco enderecoSalvo = service.salvar(endereco);
        Assertions.assertNotNull(enderecoSalvo.getId());
    }

    @Test
    void buscarTodos() throws ResourceNotFoundException, EmptyListException {
        Endereco enderecoSalvo = service.salvar(endereco);
        List<EnderecoDto> enderecoDtoList = service.buscarTodos();
        Assertions.assertTrue(enderecoDtoList.size() > 0);
    }

    @Test
    void buscarPorId() throws ResourceNotFoundException {
        Endereco enderecoBuscado = service.salvar(endereco);
        String cidade = "Cidade Teste Busca Por Id";
        enderecoBuscado.setCidade(cidade);

        EnderecoDto enderecoDto = service.buscarPorId(enderecoBuscado.getId());
        Assertions.assertEquals(cidade, enderecoBuscado.getId());
    }

    @Test
    void excluir() throws ResourceNotFoundException {
        Endereco enderecoAExcluir = service.salvar(endereco);
        Long id = enderecoAExcluir.getId();
        service.excluir(id);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(id));
    }

    @Test
    void alterar() throws ResourceNotFoundException {
        Endereco enderecoAAlterar = service.salvar(endereco);
        String rua = "Rua do Zé";
        enderecoAAlterar.setRua(rua);
        Endereco enderecoAlterado = service.alterar(enderecoAAlterar);

        Assertions.assertEquals(rua, enderecoAlterado.getRua());
    }
}