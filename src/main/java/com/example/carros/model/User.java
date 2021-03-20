package com.example.carros.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class User {

	@ApiModelProperty(required = true) //referência para o swagger
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //anotations para o hibernate
	private Long id;

	@ApiModelProperty(required = false)
	private String nome;

	@ApiModelProperty(required = false)
	private String email;

	@ApiModelProperty(required = false)
	private String login;

	@ApiModelProperty(required = false)
	private String senha;

}
