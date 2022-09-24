package com.dh.clinicaodontologica.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String rua;
    private int numero;
    private String cidade;
    private String bairro;
    private String cep;
}
