package com.example.carros.controller.carroController;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.model.Carro;
import com.example.carros.model.dto.CarroDTO;
import com.example.carros.service.carroService.SaveCarroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/carros")
public class SaveCarroController {
	
	@Autowired
	private SaveCarroService saveCarroService;

	@ApiOperation(value = "Cria um carro novo no banco de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Carro criado com sucesso"),
			@ApiResponse(code = 400, message = "Não foi possível inserir o carro"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping(produces = "application/json", consumes = "application/json")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> postCarro(@RequestBody Carro carro) {

		CarroDTO c = saveCarroService.saveCarro(carro);
		return ResponseEntity.created(URI.create("api/v1/carros/" + c.getId())).build();
	}
}
