package com.example.carros.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.carros.model.Carro;

public interface CarroRepository extends CrudRepository<Carro, Long>{

	List<Carro> findByTipo(String tipo);

}
