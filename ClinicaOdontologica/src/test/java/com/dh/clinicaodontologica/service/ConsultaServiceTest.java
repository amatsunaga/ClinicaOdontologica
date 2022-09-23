package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Consulta;
import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dh.clinicaodontologica.service.DentistaServiceTest.dentista;
@SpringBootTest
@Transactional
class ConsultaServiceTest {


    @Autowired
    ConsultaService service;

    static Consulta consulta;

    static List<Consulta> consultaList;

    @BeforeEach
    void doBefore() {
        this.consulta = new Consulta();
        this.consulta.setDentista(new Dentista());
        this.consulta.setPaciente(new Paciente());
        this.consulta.setDataHoraConsulta(LocalDateTime.now());
    }

    @Test
    void concluindoSalvamento() {
        Consulta consultaSalvar = new Consulta();
        consultaSalvar = service.salvar(consulta);

        Assertions.assertNotNull(consultaSalvar.getConsultaId());
    }

    @Test
    void buscarTodos() {
        Consulta consulta1 = new Consulta();
        Consulta consulta2 = new Consulta();
        consultaList = new ArrayList<>();
        consultaList.add(consulta1);
        consultaList.add(consulta2);
        consultaList = service.buscarTodos();

        Assertions.assertEquals(2, consultaList.size());
    }

    @Test
    void buscarPorId() {
        Consulta consultaBuscada = new Consulta();
        consultaBuscada = service.salvar(consulta);
        Optional<Consulta> consultaOptional = service.buscarPorId(3L);

        Assertions.assertEquals(3L, consultaBuscada.getConsultaId());
    }

    @Test
    void excluir() {
        Consulta consulta3 = new Consulta();
        consulta3 = service.salvar(consulta);
        Assertions.assertEquals(11L, consulta.getConsultaId());


    }

    @Test
    void alterar() {
        String sobrenome = "Andrade";
        Consulta consulta = service.buscarPorId(3L).get();
        consulta.setPaciente(new Paciente());
        consulta.setDentista(new Dentista());
        Consulta consultaAlterado = service.alterar(consulta);

        Assertions.assertEquals(sobrenome, consultaAlterado.getPaciente());
    }




}