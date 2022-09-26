package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Endereco;
import com.dh.clinicaodontologica.entity.dto.EnderecoDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class EnderecoServiceTest {

    @Autowired
    EnderecoService service;

    static Endereco endereco;

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
    void salvarTest() throws ResourceNotFoundException {
        Endereco enderecoSalvo = service.salvar(endereco);
        Assertions.assertNotNull(enderecoSalvo.getId());
    }

    @Test
    void buscarTodosTest() throws ResourceNotFoundException, EmptyListException {
        Endereco enderecoSalvo = service.salvar(endereco);
        List<EnderecoDto> enderecoDtoList = service.buscarTodos();
        Assertions.assertTrue(enderecoDtoList.size() > 0);
    }

    @Test
    void buscarPorIdTest() throws ResourceNotFoundException {
        Endereco enderecoBuscado = service.salvar(endereco);
        String cidade = "Cidade Teste Busca Por Id";
        enderecoBuscado.setCidade(cidade);

        EnderecoDto enderecoDto = service.buscarPorId(enderecoBuscado.getId());
        Assertions.assertEquals(cidade, enderecoBuscado.getCidade());
    }

    @Test
    void excluirTest() throws ResourceNotFoundException {
        Endereco enderecoAExcluir = service.salvar(endereco);
        Long id = enderecoAExcluir.getId();
        service.excluir(id);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(id));
    }

    @Test
    void alterarTest() throws ResourceNotFoundException {
        Endereco enderecoAAlterar = service.salvar(endereco);
        String rua = "Rua do Zé";
        enderecoAAlterar.setRua(rua);
        Endereco enderecoAlterado = service.alterar(enderecoAAlterar);

        Assertions.assertEquals(rua, enderecoAlterado.getRua());
    }
}