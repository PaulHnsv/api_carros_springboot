package com.example.carros.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.model.Carro;
import com.example.carros.repository.CarroRepository;


@Service
public class CarroService {
	
	@Autowired
	private CarroRepository carroRepository;
	
public Iterable<Carro> getCarros(){
	return carroRepository.findAll();
}

public Optional<Carro> getCarrosById(long id){
	return carroRepository.findById(id);
}

public Iterable<Carro> getCarrosByTipo(String tipo) {
	return carroRepository.findByTipo(tipo);
}

public Carro saveCarro(Carro carro) {
	return carroRepository.save(carro);
	
}

public Carro updateCarro(Carro carro, long id) {
	
	org.springframework.util.Assert.notNull(id, "não foi possível atualizar seu registro");
	
	//Busca o carro no banco de dados
	Optional<Carro> carroAntigo = getCarrosById(id);
	if(carroAntigo.isPresent()) {
		
		//atualiza os dados conforme passado nos parametros.
		Carro carroNovo = carroAntigo.get();
		carroNovo.setNome(carro.getNome());
		carroNovo.setTipo(carro.getTipo());
		
	carroRepository.save(carroNovo);
	
	return carroNovo;
	}
	else {
		throw new RuntimeException("não foi possível atualizar seu registro.");
	}
}

public void deleteCarro(long id) {
	Optional<Carro> carro = getCarrosById(id);
	if(carro.isPresent()) {
		carroRepository.deleteById(id);
	}
}


}
