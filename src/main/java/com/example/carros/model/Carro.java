package com.example.carros.model;

//import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@javax.persistence.Entity(name = "carro")
@AllArgsConstructor @NoArgsConstructor
@Data
public class Carro {

//@Column(nullable = false)
	

@ApiModelProperty(required = true) //referência para o swagger
@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //anotations para o hibernate
private Long id;

@ApiModelProperty(required = false)
private String nome;

@ApiModelProperty(required = false)
private String tipo;

@ApiModelProperty(required = false)
private String descricao;

@ApiModelProperty(required = false)
private String urlFoto;

@ApiModelProperty(required = false)
private String urlVideo;

@ApiModelProperty(required = false)
private String latitude;

@ApiModelProperty(required = false)
private String longitude;

}
