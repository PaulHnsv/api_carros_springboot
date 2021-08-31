package com.example.carros.service.carroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.repository.CarroRepository;

@Service
public class DeleteCarroService {

	@Autowired
	private CarroRepository carroRepository;
	

public void deleteCarro(long id) { 
	
	carroRepository.deleteById(id);

}

}
