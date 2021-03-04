package com.example.carros.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.carros.model.Carro;

public interface CarroRepository extends CrudRepository<Carro, Long>{

	Iterable<Carro> findByTipo(String tipo);

}
