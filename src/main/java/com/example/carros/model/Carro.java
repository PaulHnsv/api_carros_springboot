package com.example.carros.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@javax.persistence.Entity(name = "carro") @AllArgsConstructor @NoArgsConstructor
public class Carro {


@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
@Getter @Setter private Long id;

//@Column(name = "nome")
@Getter @Setter private String nome;

@Getter @Setter private String tipo;

}
