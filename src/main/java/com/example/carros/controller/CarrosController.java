package com.example.carros.controller;

import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.examples.carros.dto.CarroDTO;
import com.google.gson.*;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	//injeção de dependências
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity getCarros() {
		
		return ResponseEntity.ok(carroService.getCarros());
		
		//return new ResponseEntity<>(carroService.getCarros(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getCarrosById(@PathVariable("id") long id) {
		Optional<CarroDTO> carro = carroService.getCarrosById(id);		
		
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carro = carroService.getCarrosByTipo(tipo);
		
		//if ternário do java
		return carro.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carro);
	}
	
	@PostMapping
	public ResponseEntity postCarro(@RequestBody Carro carro) {

		try {
			CarroDTO c = carroService.saveCarro(carro);
			return ResponseEntity.created(URI.create("api/v1/carros/" + c.getId())).build();
		}
		catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> putCarro(@PathVariable("id") long id,@RequestBody Carro carro) {
		
		 Gson gson = new Gson();
		 
		try{
			CarroDTO c = carroService.updateCarro(carro, id);
			String jsonInString = gson.toJson(c);
			return ResponseEntity.ok().body(jsonInString);
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCarro(@PathVariable("id") long id) {
		
		try{
			carroService.deleteCarro(id);
			return ResponseEntity.ok().body("Registro deletado com sucesso");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
