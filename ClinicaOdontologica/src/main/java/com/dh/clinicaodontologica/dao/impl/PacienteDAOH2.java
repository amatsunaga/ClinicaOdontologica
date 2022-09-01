package com.dh.clinicaodontologica.dao.impl;

import com.dh.clinicaodontologica.dao.ConfiguracaoJDBC;
import com.dh.clinicaodontologica.dao.IDao;
import com.dh.clinicaodontologica.service.model.Paciente;
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
public class PacienteDAOH2 implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger logger = Logger.getLogger(PacienteDAOH2.class);

    @Override
    public Paciente salvar(Paciente paciente) throws SQLException {
        logger.info("Registrando paciente: " + paciente.getNome());
        configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
        Connection connection = configuracaoJDBC.getConnection();

        String query = String.format("INSERT INTO Paciente (nome, sobrenome, enderecoId, rg, dataCadastro)" +
                        " values('%s','%s','%s','%s','%s')",
                paciente.getNome(), paciente.getSobrenome(), paciente.getEnderecoId(), paciente.getRg(), paciente.getDataCadastro());

        try {
            Statement statement = connection.createStatement();
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                paciente.setId(resultSet.getInt(1));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() throws SQLException {
        logger.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String query = "SELECT * FROM paciente";
        List<Paciente> pacientes = new ArrayList<>();
        try {

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            logger.debug("Buscando todos os pacientes do banco");
            while (resultado.next()) {
                pacientes.add(criarObjetoPaciente(resultado));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            logger.debug("Fechando a conexão no banco");
            connection.close();
        }

        return pacientes;
    }


    public Paciente buscarPorId(int id) throws SQLException {
        logger.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM paciente WHERE ID='%s'",  id);
        Paciente paciente = null;
        try {

            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            logger.debug("Buscando um paciente");
            while (resultado.next()) {
                paciente = criarObjetoPaciente(resultado);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            logger.debug("Fechando a conexão no banco");
            connection.close();
        }

        return paciente;
    }

    @Override
    public void excluir(int id) throws SQLException {
        logger.info("Abrindo conexão");
        Connection connection = null;
        Statement stmt = null;
        String SQLDelete = String.format("DELETE FROM paciente WHERE id = %s", id);
        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            logger.debug("Excluindo paciente com id: " + id);
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
    public void alterar(Paciente paciente) throws SQLException {


        String SQLUpdate = String.format("UPDATE paciente SET enderecoId = '%s' where id = '%s';",
                paciente.getEnderecoId(),paciente.getId());
        Connection connection = null;
        try{
            logger.info("Alterando endereço do paciente: "+paciente.getId());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/paciente;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLUpdate);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Erro ao alterar endereço: "+ e.getMessage());
        }finally {
            logger.info("Fechando conexão");
            connection.close();
        }
    }

    private Paciente criarObjetoPaciente(ResultSet result) throws SQLException {

        Integer id = result.getInt("ID");
        String nome = result.getString("nome");
        String sobrenome = result.getString("sobrenome");
        int enderecoId = result.getInt("enderecoId");
        String rg = result.getString("rg");
        LocalDate dataCadastro = result.getDate("dataCadastro").toLocalDate();
        return new Paciente(id, nome, sobrenome, enderecoId, rg, dataCadastro);

    }

}
