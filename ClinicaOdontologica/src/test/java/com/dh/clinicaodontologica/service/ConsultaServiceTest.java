package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Consulta;
import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.entity.dto.ConsultaDto;
import com.dh.clinicaodontologica.exception.EmptyListException;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@Transactional
class ConsultaServiceTest {


    @Autowired
    ConsultaService service;

    @Autowired
    static Dentista dentista;

    @Autowired
    static Paciente paciente;

    static Consulta consulta;
    
    @BeforeEach
    void doBefore() {
        this.consulta = new Consulta();
        this.consulta.setDentista(this.dentista);
        this.consulta.setPaciente(this.paciente);
        this.consulta.setDataConsulta(LocalDate.now());
        this.consulta.setHorarioConsulta(LocalTime.now());
    }

    @Test
    void salvarTest() throws ResourceNotFoundException {
        Consulta consultaSalva = service.salvar(consulta);
        Assertions.assertNotNull(consultaSalva.getConsultaId());
    }

    @Test
    void buscarTodosTest() throws ResourceNotFoundException, EmptyListException {
        Consulta consultaSalva = service.salvar(this.consulta);
        List<ConsultaDto> consultaDtoList = service.buscarTodos();
        Assertions.assertTrue(consultaDtoList.size() > 0);
    }

    @Test
    void buscarPorIdTest() throws ResourceNotFoundException {
        Consulta consultaBuscada = service.salvar(consulta);
        LocalTime horaTeste = LocalTime.of(03, 59);
        consultaBuscada.setHorarioConsulta(horaTeste);

        ConsultaDto consultaDto = service.buscarPorId(consultaBuscada.getConsultaId());

        Assertions.assertEquals(horaTeste, consultaBuscada.getHorarioConsulta());
    }

    @Test
    void excluirTest() throws ResourceNotFoundException {
        Consulta consulta3 = service.salvar(consulta);
        Long id = consulta3.getConsultaId();
        service.excluir(id);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(id));
    }

    @Test
    void alterarTest() throws ResourceNotFoundException {
        Consulta consultaAAlterar = service.salvar(this.consulta);
        LocalDate dataTeste = LocalDate.of(2020, 03, 19);
        consultaAAlterar.setDataConsulta(dataTeste);
        Consulta consultaAlterada = service.alterar(consultaAAlterar);
        Assertions.assertEquals(dataTeste, consultaAlterada.getDataConsulta());
    }


}