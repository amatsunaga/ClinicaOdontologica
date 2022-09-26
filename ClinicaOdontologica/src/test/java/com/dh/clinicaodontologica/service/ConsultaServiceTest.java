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
import java.time.LocalDateTime;
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
        this.dentista.setNome("José");
        this.dentista.setSobrenome("Ferreira");
        this.dentista.setMatricula("12345");

        this.paciente = new Paciente();
        this.paciente.setNome("Paciente");
        this.paciente.setSobrenome("Teste");
//        this.paciente.setEndereco(this.endereco);
        this.paciente.setRg("1234567");
//        this.paciente.setDataCadastro(LocalDate.now());

        this.consulta = new Consulta();
        this.consulta.setDentista(this.dentista);
        this.consulta.setPaciente(this.paciente);
        this.consulta.setDataHoraConsulta(LocalDateTime.now());
    }

    @Test
    void salvarTest() throws ResourceNotFoundException {
        dentistaService.salvar(this.dentista);
        pacienteService.salvar(this.paciente);
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
        dentistaService.salvar(this.dentista);
        pacienteService.salvar(this.paciente);
        Consulta consultaBuscada = consultaService.salvar(consulta);
        LocalDateTime dataHoraConsulta = LocalDateTime.of(2100, 01, 01, 03, 59);
        consultaBuscada.setDataHoraConsulta(dataHoraConsulta);

        ConsultaDto consultaDto = consultaService.buscarPorId(consultaBuscada.getConsultaId());

        Assertions.assertEquals(dataHoraConsulta, consultaBuscada.getDataHoraConsulta());
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
        dentistaService.salvar(this.dentista);
        pacienteService.salvar(this.paciente);
        Consulta consulta3 = consultaService.salvar(consulta);
        Long id = consulta3.getConsultaId();
        consultaService.excluir(id);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> consultaService.buscarPorId(id));
    }

    @Test
    void alterarTest() throws ResourceNotFoundException {
        dentistaService.salvar(this.dentista);
        pacienteService.salvar(this.paciente);
        Consulta consultaAAlterar = consultaService.salvar(this.consulta);
        LocalDateTime dataHoraTeste = LocalDateTime.of(2020, 03, 19, 11, 11);
        consultaAAlterar.setDataHoraConsulta(dataHoraTeste);
        Consulta consultaAlterada = consultaService.alterar(consultaAAlterar);
        Assertions.assertEquals(dataHoraTeste, consultaAlterada.getDataHoraConsulta());
    }


}