package com.example.carros.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.dto.CarroDTO;
import com.example.carros.model.Carro;
import com.example.carros.repository.CarroRepository;



@Service
public class CarroService {
	
	@Autowired
	private CarroRepository carroRepository;
	
public List<CarroDTO> getCarros(){
	
	//lambda
	return carroRepository.findAll()
			.stream()
			.map(CarroDTO::create)
			.collect(Collectors.toList());
	
//	List<CarroDTO> list = new ArrayList<>();
//	for (Carro c : carros) {
//		list.add(new CarroDTO(c));
//	}
	
}

public Optional<CarroDTO> getCarrosById(long id){
	
	//lambda
	return carroRepository.findById(id).map(CarroDTO::create);
}

public List<CarroDTO> getCarrosByTipo(String tipo) {
	
	return carroRepository.findByTipo(tipo)
			.stream()
			.map(CarroDTO::create)
			.collect(Collectors.toList());
}

public CarroDTO saveCarro(Carro carro) {
	org.springframework.util.Assert.isNull(carro.getId(), "Não foi possível inserir seu registro, o campo ID não pode ser enviado junto ao json");
		return CarroDTO.create(carroRepository.save(carro));

}

public CarroDTO updateCarro(Carro carro, long id) {
	
	org.springframework.util.Assert.notNull(id, "não foi possível atualizar seu registro");
	
	//Busca o carro no banco de dados
	Optional<Carro> carroAntigo = carroRepository.findById(id);
	if(carroAntigo.isPresent() && carro.getNome() != null && carro.getTipo() != null) {
		
		//atualiza os dados conforme passado nos parametros.
		Carro carroNovo = carroAntigo.get();
		carroNovo.setNome(carro.getNome());
		carroNovo.setTipo(carro.getTipo());
		
	carroRepository.save(carroNovo);
	
	return CarroDTO.create(carroNovo);
	}
	else {
		throw new RuntimeException("Não foi possível atualizar seu registro.");
	}
}

public void deleteCarro(long id) { 
	
	carroRepository.deleteById(id);

}


}
