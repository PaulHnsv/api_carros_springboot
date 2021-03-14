package com.example.carros.model;

import javax.persistence.Column;
//import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@javax.persistence.Entity(name = "carro")
@AllArgsConstructor @NoArgsConstructor
public class Carro {

//@Column(nullable = false)
	

@ApiModelProperty(required = true) //referência para o swagger
@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //anotations para o hibernate
@Getter @Setter private Long id;

@ApiModelProperty(required = false)
@Getter @Setter private String nome;

@ApiModelProperty(required = false)
@Getter @Setter private String tipo;

@ApiModelProperty(required = false)
@Getter @Setter private String descricao;

@ApiModelProperty(required = false)
@Getter @Setter private String urlFoto;

@ApiModelProperty(required = false)
@Getter @Setter private String urlVideo;

@ApiModelProperty(required = false)
@Getter @Setter private String latitude;

@ApiModelProperty(required = false)
@Getter @Setter private String longitude;

}
