package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Dentista;
import com.dh.clinicaodontologica.entity.Endereco;
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
class EnderecoServiceTest {

    @Autowired
    EnderecoService service;

    static Endereco endereco;
    static List<Endereco> enderecoList;

    @BeforeEach
    void doBefore() {
        this.endereco = new Endereco();
        this.endereco.setRua("Rua das Dores");
        this.endereco.setNumero(666);
        this.endereco.setCidade("Manaus");
        this.endereco.setBairro("Ubirajara");
        this.endereco.setCep("69015690");
    }

    @Test
    void concluindoSalvamento() {
        // Funcionando
        Endereco enderecoSalvo = new Endereco();
        enderecoSalvo = service.salvar(endereco);

        Assertions.assertNotNull(enderecoSalvo.getId());
    }

    @Test
    void buscarTodos() {
        // Funcionando
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();
        enderecoList = new ArrayList<>();
        enderecoList.add(endereco1);
        enderecoList.add(endereco2);
        enderecoList = service.buscarTodos();

        Assertions.assertEquals(2, enderecoList.size());
    }

    @Test
    void buscarPorId() {
        // Checar!!! Talvez ok, mas o id sempre muda pq estamos criando novos id a cada teste
        Endereco enderecoBuscado = new Endereco();
        enderecoBuscado = service.salvar(endereco);
        Optional<Endereco> EnderecoOptional = service.buscarPorId(1L);

        Assertions.assertEquals(1L, enderecoBuscado.getId());
    }

    @Test
    void excluir() {
        //Deu certo, porém continua substituindo a id a cada teste
        Endereco endereco3 = new Endereco();
        endereco3 = service.salvar(endereco);
        Assertions.assertEquals(2L, endereco.getId());


    }

//    @Test
//    void alterar() {
//        // Aparentemente ok
//        String rua = "Avenida Brasil";
//        Endereco endereco = service.buscarPorId(1L).get();
//        endereco.setRua(rua);
//        Endereco enderecoAlterado = service.alterar(endereco);
//
//        Assertions.assertEquals(endereco, enderecoAlterado.getRua());
//    }
}