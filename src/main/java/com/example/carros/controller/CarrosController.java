package com.example.carros.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.model.Carro;
import com.example.carros.service.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	//injeção de dependências
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public Iterable<Carro> getCarros() {
		return carroService.getCarros();
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> getCarrosById(@PathVariable("id") long id) {
		return carroService.getCarrosById(id);
	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		return carroService.getCarrosByTipo(tipo);
	}
	
	@PostMapping
	public String postCarro(@RequestBody Carro carro) {
		Carro c = carroService.saveCarro(carro);
		
		return "carro salvo com sucesso: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String putCarro(@PathVariable("id") long id,@RequestBody Carro carro) {
		Carro c = carroService.updateCarro(carro, id);
		return "carro atualizado com sucesso: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String deleteCarro(@PathVariable("id") long id) {
		carroService.deleteCarro(id);
		return "carro deletado com sucesso.";
	}
}
