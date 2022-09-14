//package com.dh.clinicaodontologica.dao.impl;
//
//import com.dh.ecommerceMVC.dao.ConfiguracaoJDBC;
//import com.dh.ecommerceMVC.dao.IDao;
//import com.dh.ecommerceMVC.model.Produto;
//import org.apache.log4j.Logger;
//import org.springframework.context.annotation.Configuration;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//@Configuration
//public class ProdutoDAOH2 implements IDao<Produto> {
//
//    private ConfiguracaoJDBC configuracaoJDBC;
//
//    final static Logger log = Logger.getLogger(ProdutoDAOH2.class);
//
//
//
//    @Override
//    public Produto salvar(Produto produto) throws SQLException {
//        log.info("Abrindo conexão");
//
//        String SQLInsert = String.format("INSERT INTO produto (nomeProduto, valor)" +
//                " VALUES ('%s','%s')", produto.getNomeProduto(), produto.getValor());
//        Connection connection = null;
//        try{
//            log.info("Salvando produto: "+produto.getNomeProduto());
//            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/produto;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
//            connection = configuracaoJDBC.getConnection();
//            Statement statement = connection.createStatement();
//
//            statement.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);
//
//            ResultSet resultSet = statement.getGeneratedKeys();
//
//            if(resultSet.next())
//                produto.setId(resultSet.getInt(1));
//
//        }catch (Exception e){
//            e.printStackTrace();
//            log.error("Erro ao inserir produto: "+ e.getMessage());
//        }finally {
//            log.info("Fechando conexão");
//            connection.close();
//        }
//        return produto;
//    }
//
//    @Override
//    public List<Produto> buscarTodos() {
//        return null;
//    }
//}
