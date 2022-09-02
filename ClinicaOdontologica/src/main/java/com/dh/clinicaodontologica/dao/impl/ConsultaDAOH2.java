package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.ConfiguracaoJDBC;
import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.model.Consulta;
import com.dh.clinicaodontologica.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsultaDAOH2 implements IDao<Consulta> {

    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger logger = Logger.getLogger(ConsultaDAOH2.class);

    @Override
    public Consulta salvar(Consulta consulta) throws SQLException {
        logger.info("Registrando consulta: " + consulta.getDataConsulta());
        configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
        Connection connection = configuracaoJDBC.getConnection();

        String query = String.format("INSERT INTO Consulta (dentistaId, pacienteId, dataConsulta) VALUES ('%s','%s','%s')", consulta.getDentistaId(), consulta.getPacienteId(), consulta.getDataConsulta());

        try {
            Statement statement = connection.createStatement();
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                consulta.setConsultaId(resultSet.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return consulta;
    }

    @Override
    public List<Consulta> buscarTodos() throws SQLException {
        logger.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String query = "SELECT * FROM Consulta";
        List<Consulta> consultas = new ArrayList<>();

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            logger.debug("Buscando todas as consultas do banco");
            while (resultado.next()) {
                consultas.add(criarObjetoConsulta(resultado));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            logger.debug("Fechando a conexão no banco");
            connection.close();
        }

        return consultas;
    }

    public List<Consulta> buscarPorPaciente(int id) throws SQLException {
        logger.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM Consulta WHERE pacienteId = '%s'",  id);
        List<Consulta> consultas = new ArrayList<>();

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            logger.debug("Buscando todas as consultas do paciente " + id);
            while (resultado.next()) {
                consultas.add(criarObjetoConsulta(resultado));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            logger.debug("Fechando a conexão no banco");
            connection.close();
        }

        return consultas;
    }

    public List<Consulta> buscarPorDentista(int id) throws SQLException {
        logger.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM Consulta WHERE dentistaId = '%s'",  id);
        List<Consulta> consultas = new ArrayList<>();

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            logger.debug("Buscando todas as consultas do dentista " + id);
            while (resultado.next()) {
                consultas.add(criarObjetoConsulta(resultado));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            logger.debug("Fechando a conexão no banco");
            connection.close();
        }

        return consultas;
    }
    @Override
    public void excluir(int id) throws SQLException {
        logger.info("Abrindo conexão");
        Connection connection = null;
        Statement stmt = null;
        String SQLDelete = String.format("DELETE FROM Consulta WHERE consultaId = '%s'", id);

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            logger.debug("Excluindo consulta com id: " + id);
            stmt = connection.createStatement();
            stmt.execute(SQLDelete);
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            logger.debug("Fechando conexão*");
            connection.close();
        }
    }

    @Override
    public void alterar(Consulta consulta) throws SQLException {
        String SQLUpdate = String.format("UPDATE Consulta SET dataConsulta = '%s' where consultaId = '%s';",
                consulta.getDataConsulta(),consulta.getConsultaId());
        Connection connection = null;

        try {
            logger.info("Alterando data da consulta do paciente: " + consulta.getPacienteId());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQLUpdate);

        } catch (Exception e){
            e.printStackTrace();
            logger.error("Erro ao alterar endereço: "+ e.getMessage());

        } finally {
            logger.info("Fechando conexão");
            connection.close();
        }
    }

    private Consulta criarObjetoConsulta(ResultSet result) throws SQLException {

        int consultaId = result.getInt("consultaId");
        int dentistaId = result.getInt("dentistaId");
        int pacienteId = result.getInt("pacienteId");
        LocalDate dataConsulta = result.getDate("dataConsulta").toLocalDate();

        return new Consulta(consultaId, dentistaId, pacienteId, dataConsulta);
    }
}
