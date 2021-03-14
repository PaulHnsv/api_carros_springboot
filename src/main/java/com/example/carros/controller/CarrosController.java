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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	//injeção de dependências
	@Autowired
	private CarroService carroService;
	

	//customização de códigos de retorno para o swagger
	@ApiOperation(value = "Retorna uma lista de carros")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de carros"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Lista de carros não encontrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping(produces="application/json")
	public ResponseEntity getCarros() {
		
		return ResponseEntity.ok(carroService.getCarros());
		
		//return new ResponseEntity<>(carroService.getCarros(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna um carro específico")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna o carro identificado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Carro não encontrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity getCarrosById(@PathVariable("id") long id) {
		Optional<CarroDTO> carro = carroService.getCarrosById(id);		
		
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Retorna uma lista de carros filtrando por seu tipo")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de carros"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Nenhum carro foi não encontrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping(value = "/tipo/{tipo}", produces="application/json")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carro = carroService.getCarrosByTipo(tipo);
		
		//if ternário do java
		return carro.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carro);
	}
	
	@ApiOperation(value = "Cria um carro novo no banco de dados")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Carro criado com sucesso"),
		    @ApiResponse(code = 400, message = "Não foi possível inserir o carro"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity postCarro(@RequestBody Carro carro) {

		try {
			CarroDTO c = carroService.saveCarro(carro);
			return ResponseEntity.created(URI.create("api/v1/carros/" + c.getId())).build();
		}
		catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Atualiza um determinado carro")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Carro atualizado com sucesso"),
		    @ApiResponse(code = 400, message = "Não foi possível atualizar o carro"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
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
	
	@ApiOperation(value = "Deleta um determinado carro")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Carro deletado com sucesso"),
		    @ApiResponse(code = 400, message = "Não foi possível deletar o carro"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@DeleteMapping(value = "/{id}", produces="application/json")
	public ResponseEntity deleteCarro(@PathVariable("id") long id) {
		
		try{
			carroService.deleteCarro(id);
			return ResponseEntity.ok().body("Carro deletado com sucesso");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
