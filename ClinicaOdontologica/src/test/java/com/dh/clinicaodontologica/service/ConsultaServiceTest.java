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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    ConsultaService consultaService;

    @Autowired
    DentistaService dentistaService;

    @Autowired
    PacienteService pacienteService;

    static Dentista dentista;

    static Paciente paciente;

    static Consulta consulta;

    @BeforeEach
    void doBefore() {
        this.dentista = new Dentista();

        this.paciente = new Paciente();

        this.consulta = new Consulta();
        this.consulta.setDentista(this.dentista);
        this.consulta.setPaciente(this.paciente);
        this.consulta.setDataConsulta(LocalDate.now());
        this.consulta.setHorarioConsulta(LocalTime.now());
    }

    @Test
    void salvarTest() throws ResourceNotFoundException {
        Consulta consultaSalva = consultaService.salvar(consulta);
        Assertions.assertNotNull(consultaSalva.getConsultaId());
    }

    @Test
    void buscarTodosTest() throws ResourceNotFoundException, EmptyListException {
        dentistaService.salvar(this.dentista);
        pacienteService.salvar(this.paciente);

        Consulta consultaSalva = consultaService.salvar(this.consulta);
        List<ConsultaDto> consultaDtoList = consultaService.buscarTodos();
        Assertions.assertTrue(consultaDtoList.size() > 0);
    }

    @Test
    void buscarPorIdTest() throws ResourceNotFoundException {
        Consulta consultaBuscada = consultaService.salvar(consulta);
        LocalTime horaTeste = LocalTime.of(03, 59);
        consultaBuscada.setHorarioConsulta(horaTeste);

        ConsultaDto consultaDto = consultaService.buscarPorId(consultaBuscada.getConsultaId());

        Assertions.assertEquals(horaTeste, consultaBuscada.getHorarioConsulta());
    }

    @ParameterizedTest
    @ValueSource(strings = {"DentistaTeste"})
    void buscarPorDentista(String nome) throws ResourceNotFoundException, EmptyListException {
        pacienteService.salvar(this.paciente);

        Dentista dentistaSalvo = dentistaService.salvar(this.dentista);
        String nomeDentista = "DentistaTeste";
        dentistaSalvo.setNome(nomeDentista);

        Consulta consultaSalva = consultaService.salvar(this.consulta);

        List<ConsultaDto> consultaDtoList = consultaService.buscarPorDentista(nome);
        Assertions.assertTrue(consultaDtoList.size() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PacienteTeste"})
    void buscarPorPaciente(String nome) throws ResourceNotFoundException, EmptyListException {
        dentistaService.salvar(this.dentista);

        Paciente pacienteSalvo = pacienteService.salvar(this.paciente);
        String nomePaciente = "PacienteTeste";
        pacienteSalvo.setNome(nomePaciente);

        Consulta consultaSalva = consultaService.salvar(this.consulta);

        List<ConsultaDto> consultaDtoList = consultaService.buscarPorPaciente(nome);
        Assertions.assertTrue(consultaDtoList.size() > 0);
    }

    @Test
    void excluirTest() throws ResourceNotFoundException {
        Consulta consulta3 = consultaService.salvar(consulta);
        Long id = consulta3.getConsultaId();
        consultaService.excluir(id);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> consultaService.buscarPorId(id));
    }

    @Test
    void alterarTest() throws ResourceNotFoundException {
        Consulta consultaAAlterar = consultaService.salvar(this.consulta);
        LocalDate dataTeste = LocalDate.of(2020, 03, 19);
        consultaAAlterar.setDataConsulta(dataTeste);
        Consulta consultaAlterada = consultaService.alterar(consultaAAlterar);
        Assertions.assertEquals(dataTeste, consultaAlterada.getDataConsulta());
    }


}