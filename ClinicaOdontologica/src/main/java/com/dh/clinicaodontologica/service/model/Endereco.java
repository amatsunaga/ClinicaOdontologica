package com.dh.clinicaodontologica.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private int id;
    private String rua;
    private int numero;
    private String cidade;
    private String bairro;
    private String cep;
}
