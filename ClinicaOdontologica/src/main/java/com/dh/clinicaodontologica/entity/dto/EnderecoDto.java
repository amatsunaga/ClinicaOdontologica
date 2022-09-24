package com.dh.clinicaodontologica.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDto {

    @NotNull
    private String rua;

    @NotNull
    private String numero;

    @NotNull
    private String cidade;

    private String bairro;

    private String cep;
}
