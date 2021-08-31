package com.example.carros.service.carroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.model.Carro;
import com.example.carros.model.dto.CarroDTO;
import com.example.carros.repository.CarroRepository;

@Service
public class SaveCarroService {
	
	@Autowired
	private CarroRepository carroRepository;

	public CarroDTO saveCarro(Carro carro) {
		org.springframework.util.Assert.isNull(carro.getId(), "Não foi possível inserir seu registro, o campo ID não pode ser enviado junto ao json");
			return CarroDTO.create(carroRepository.save(carro));

	}
}
