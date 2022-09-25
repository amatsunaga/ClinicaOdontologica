package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.dto.DentistaDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DentistaServiceTest {
    @Autowired
    DentistaService service;

    static Dentista dentista;
    static List<Dentista> dentistaList;

    @BeforeEach
    void doBefore() {
        this.dentista = new Dentista();
        this.dentista.setNome("José");
        this.dentista.setSobrenome("Ferreira");
        this.dentista.setMatricula("12345");
    }

    @Test
    void salvarTest() throws ResourceNotFoundException {
        Dentista dentistaSalvo = service.salvar(this.dentista);
        Assertions.assertNotNull(dentistaSalvo.getId());
    }

    @Test
    void buscarTodosTest() throws ResourceNotFoundException, EmptyListException {
        Dentista dentistaSalvo = service.salvar(this.dentista);
        List<DentistaDto> dentistaDtoList = service.buscarTodos();
        Assertions.assertTrue(dentistaDtoList.size() > 0);
    }

    @Test
    void buscarPorIdTest() throws ResourceNotFoundException {
        Dentista dentistaSalvo = service.salvar(this.dentista);
        String nome = "Pedro";
        dentistaSalvo.setNome(nome);
        DentistaDto dentistaDto = service.buscarPorId(dentistaSalvo.getId());
        Assertions.assertEquals("Pedro", dentistaDto.getNome());
    }

    @Test
    void buscarPorMatriculaTest() throws ResourceNotFoundException {
        Dentista dentistaSalvo = service.salvar(this.dentista);
        DentistaDto dentistaDto = service.buscarPorMatricula("12345");
        Assertions.assertEquals("12345", dentistaDto.getMatricula());
    }

    @Test
    void excluirTest() throws ResourceNotFoundException {
        Dentista dentistaAExcluir = service.salvar(this.dentista);
        Long id = dentistaAExcluir.getId();
        service.excluir(id);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(id));
    }

    @Test
    void alterarTest() throws ResourceNotFoundException {
        Dentista dentistaAAlterar = service.salvar(this.dentista);
        String sobrenome = "Silva";
        dentistaAAlterar.setSobrenome(sobrenome);
        Dentista dentistaAlterado = service.alterar(dentistaAAlterar);
        Assertions.assertEquals(sobrenome, dentistaAlterado.getSobrenome());
    }
}