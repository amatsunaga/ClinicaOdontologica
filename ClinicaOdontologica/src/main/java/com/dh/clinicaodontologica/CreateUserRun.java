package com.dh.clinicaodontologica;

import com.dh.clinicaodontologica.entity.Perfil;
import com.dh.clinicaodontologica.entity.Usuario;
import com.dh.clinicaodontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreateUserRun implements ApplicationRunner {


    @Autowired
    UsuarioRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //Criando nosso perfis
        Perfil perfilDentista = new Perfil();
        Perfil perfilPaciente = new Perfil();

        perfilDentista.setDescricao("ADMIN");
        perfilPaciente.setDescricao("USER");

        //Criando nossas listas de perfis
        List<Perfil> perfilList1 = new ArrayList<>();
        List<Perfil> perfilList2 = new ArrayList<>();

        //Populando listas de perfis
        perfilList1.add(perfilDentista);
        perfilList2.add(perfilPaciente);

        //Criando nosso usuarios
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Usuario usuario3 = new Usuario();

        //Populando usuario Dentista
        usuario1.setPassword(encoder.encode("123456"));
        usuario1.setUsername("Gabriel");
        usuario1.setPerfis(perfilList1);

        //Populando usuario Paciente
        usuario2.setPassword(encoder.encode("1234567"));
        usuario2.setUsername("Roberto");
        usuario2.setPerfis(perfilList2);

        repository.save(usuario1);
        repository.save(usuario2);
    }
}