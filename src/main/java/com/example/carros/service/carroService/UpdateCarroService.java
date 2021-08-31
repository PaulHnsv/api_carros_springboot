package com.example.carros.service.carroService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.model.Carro;
import com.example.carros.model.dto.CarroDTO;
import com.example.carros.repository.CarroRepository;

@Service
public class UpdateCarroService {

	@Autowired
	private CarroRepository carroRepository;
	

public CarroDTO updateCarro(Carro carro, long id) {
	
	org.springframework.util.Assert.notNull(id, "não foi possível atualizar seu registro, o parâmetro id do carro enviado está nulo");
	org.springframework.util.Assert.notNull(carro.getNome(),"não foi possível atualizar seu registro, o campo nome do carro enviado está nulo");
	org.springframework.util.Assert.notNull(carro.getTipo(), "não foi possível atualizar seu registro, o campo tipo do carro enviado está nulo");
	
	Optional<Carro> carroAntigo = carroRepository.findById(id);

	Carro carroNovo = carroAntigo.get();
	carroNovo.setNome(carro.getNome());
	carroNovo.setTipo(carro.getTipo());
	carroRepository.save(carroNovo);
	
	return CarroDTO.create(carroNovo);

	}
}
