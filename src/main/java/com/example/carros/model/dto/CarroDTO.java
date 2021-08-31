package com.example.carros.model.dto;

import org.modelmapper.ModelMapper;

import com.example.carros.model.Carro;

import lombok.Data;

@Data
public class CarroDTO {
	
	private Long id;
	private String nome;
	private String tipo;
//	private String descricao;
//	private String urlFoto;
//	private String urlVideo;
//	private String latitude;
//	private String longitude;

//	public CarroDTO(Carro c) {
//		this.id = c.getId();
//		this.nome = c.getNome();
//		this.tipo = c.getTipo();
//	}

	public static CarroDTO create(Carro carro) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(carro, CarroDTO.class);
	}


}
