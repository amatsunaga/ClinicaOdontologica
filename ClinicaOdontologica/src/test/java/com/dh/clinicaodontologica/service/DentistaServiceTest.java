package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Dentista;
import javax.transaction.Transactional;
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
    void concluindoSalvamento() {
        // Funcionando
        Dentista dentistaSalvo = new Dentista();
        dentistaSalvo = service.salvar(dentista);

        Assertions.assertNotNull(dentistaSalvo.getId());
    }

    @Test
    void buscarTodos() {
        // Funcionando
        Dentista dentista1 = new Dentista();
        Dentista dentista2 = new Dentista();
        dentistaList = new ArrayList<>();
        dentistaList.add(dentista1);
        dentistaList.add(dentista2);
        dentistaList = service.buscarTodos();

        Assertions.assertEquals(2, dentistaList.size());
    }

    @Test
    void buscarPorId() {
        // Checar!!! Talvez ok, mas o id sempre muda pq estamos criando novos id a cada teste
        Dentista dentistaBuscado = new Dentista();
        dentistaBuscado = service.salvar(dentista);
        Optional<Dentista> dentistaOptional = service.buscarPorId(1L);

        Assertions.assertEquals(3L, dentistaBuscado.getId());
    }

    @Test
    void excluir() {
        //Deu certo, porém continua substituindo a id a cada teste
        Dentista dentista3 = new Dentista();
        dentista3 = service.salvar(dentista);
        Assertions.assertEquals(11L, dentista.getId());


    }

    @Test
    void alterar() {
        // Aparentemente ok
        String sobrenome = "Silva";
        Dentista dentista = service.buscarPorId(1L).get();
        dentista.setSobrenome(sobrenome);
        Dentista dentistaAlterado = service.alterar(dentista);

        Assertions.assertEquals(sobrenome, dentistaAlterado.getSobrenome());
    }
}