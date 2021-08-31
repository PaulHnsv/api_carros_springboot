package com.example.carros.service.carroService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.exception.ObjectNotFoundException;
import com.example.carros.model.Carro;
import com.example.carros.model.dto.CarroDTO;
import com.example.carros.repository.CarroRepository;

@Service
public class GetCarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	public List<CarroDTO> getCarros(){
		
		//lambda
		return carroRepository.findAll()
				.stream()
				.map(CarroDTO::create)
				.collect(Collectors.toList());
		
	}
	
	public CarroDTO getCarrosById(long id){
		Optional<Carro> carro = carroRepository.findById(id);
		
		//lambda
		return carro.map(CarroDTO::create)
				.orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado")) ;
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		
		List<Carro> carros = carroRepository.findByTipo(tipo);
		String error = "Nenhum carro encontrado, a lista está vazia";
		
		try {
			org.springframework.util.Assert.notEmpty(carros, error);
		} catch (Exception exception) {
			
		}
		
		return carros.stream()
				.map(CarroDTO::create)
				.collect(Collectors.toList());
	}
}
