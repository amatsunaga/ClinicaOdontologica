package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Perfil;
import com.dh.clinicaodontologica.entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AutenticacaoServiceTest {

    @Autowired
    AutenticacaoService service;

    static Usuario usuario;

    @BeforeEach
    void doBefore() {
        this.usuario = new Usuario();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Dentista"})
    void loadUserByUsernameTest(String username) throws UsernameNotFoundException {
        usuario.setUsername("Dentista");
        service.loadUserByUsername(username);

        Assertions.assertEquals(username, usuario.getUsername());

    }
}