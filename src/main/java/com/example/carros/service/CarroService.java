package com.example.carros.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.dto.CarroDTO;
import com.example.carros.exception.ObjectNotFoundException;
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

public CarroDTO getCarrosById(long id){
	Optional<Carro> carro = carroRepository.findById(id);
	
	//lambda
	return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado")) ;
}

public List<CarroDTO> getCarrosByTipo(String tipo) {
	
	List<Carro> carros = carroRepository.findByTipo(tipo);
	
	org.springframework.util.Assert.notEmpty(carros, "Nenhum carro encontrado, a lista está vazia");
	
	return carros.stream().map(CarroDTO::create).collect(Collectors.toList());
}

public CarroDTO saveCarro(Carro carro) {
	org.springframework.util.Assert.isNull(carro.getId(), "Não foi possível inserir seu registro, o campo ID não pode ser enviado junto ao json");
		return CarroDTO.create(carroRepository.save(carro));

}

public CarroDTO updateCarro(Carro carro, long id) {
	
	org.springframework.util.Assert.notNull(id, "não foi possível atualizar seu registro, o parâmetro id do carro enviado está nulo");
	org.springframework.util.Assert.notNull(carro.getNome(),"não foi possível atualizar seu registro, o campo nome do carro enviado está nulo");
	org.springframework.util.Assert.notNull(carro.getTipo(), "não foi possível atualizar seu registro, o campo tipo do carro enviado está nulo");
	
	//Busca o carro no banco de dados
	Optional<Carro> carroAntigo = carroRepository.findById(id);

	// atualiza os dados conforme passado nos parametros.
	Carro carroNovo = carroAntigo.get();
	carroNovo.setNome(carro.getNome());
	carroNovo.setTipo(carro.getTipo());
	carroRepository.save(carroNovo);
	
	return CarroDTO.create(carroNovo);

}

public void deleteCarro(long id) { 
	
	carroRepository.deleteById(id);

}


}
