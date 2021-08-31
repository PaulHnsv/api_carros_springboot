package com.example.carros.controller.carroController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.service.carroService.DeleteCarroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/carros")
public class DeleteCarroController {

	@Autowired
	private DeleteCarroService deleteCarroService;
	
	
	@ApiOperation(value = "Deleta um determinado carro")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Carro deletado com sucesso"),
		    @ApiResponse(code = 400, message = "Não foi possível deletar o carro"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@DeleteMapping(value = "/{id}", produces="application/json")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> deleteCarro(@PathVariable("id") long id) {
		
		deleteCarroService.deleteCarro(id);
			return ResponseEntity.noContent().build();
		
	}
}
