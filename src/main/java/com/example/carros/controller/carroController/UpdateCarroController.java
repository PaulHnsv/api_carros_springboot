package com.example.carros.controller.carroController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.model.Carro;
import com.example.carros.model.dto.CarroDTO;
import com.example.carros.service.carroService.UpdateCarroService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/carros")
public class UpdateCarroController {
	
	@Autowired
	private UpdateCarroService updateCarroService;
	
	private Gson gson = new Gson();

	@ApiOperation(value = "Atualiza um determinado carro")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Carro atualizado com sucesso"),
		    @ApiResponse(code = 400, message = "Não foi possível atualizar o carro"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> putCarro(@PathVariable("id") long id,@RequestBody Carro carro) {
		
		try{
			CarroDTO c = updateCarroService.updateCarro(carro, id);
			String jsonInString = gson.toJson(c);
			return ResponseEntity.ok().body(jsonInString);
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
			
	}
}
