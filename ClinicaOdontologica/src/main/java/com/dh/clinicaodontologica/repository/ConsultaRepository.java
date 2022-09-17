package com.dh.clinicaodontologica.repository;

import com.dh.clinicaodontologica.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query("SELECT c FROM Consulta c WHERE c.dentista.nome = :nome")
    List<Consulta> findByDentistaNome(@Param("nome") String nome);
}
