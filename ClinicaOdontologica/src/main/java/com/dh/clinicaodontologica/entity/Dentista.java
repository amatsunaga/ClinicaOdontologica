package com.dh.clinicaodontologica.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
//    @Column(nullable = false)
    private String nome;

    @NotNull
//    @Column(nullable = false)
    private String sobrenome;

    @NotNull
    @Column(unique = true)
    private String matricula;

}
