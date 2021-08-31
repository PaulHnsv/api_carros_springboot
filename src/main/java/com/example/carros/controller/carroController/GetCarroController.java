package com.example.carros.controller.carroController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.model.dto.CarroDTO;
import com.example.carros.service.carroService.GetCarroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/carros")
public class GetCarroController {
	
	@Autowired
	private GetCarroService getCarroService;

	//customização de códigos de retorno para o swagger
		@ApiOperation(value = "Retorna uma lista de carros")
		@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Retorna a lista de carros"),
			    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			    @ApiResponse(code = 404, message = "Lista de carros não encontrado"),
			    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
			})
		@GetMapping(produces="application/json")
		@Secured({ "ROLE_USER", "ROLE_ADMIN" })
		public ResponseEntity<?> getCarros() {
			
			return ResponseEntity.ok(getCarroService.getCarros());
				}
		
		@ApiOperation(value = "Retorna um carro específico")
		@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Retorna o carro identificado"),
			    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			    @ApiResponse(code = 404, message = "Carro não encontrado"),
			    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
			})
		@GetMapping(value = "/{id}", produces="application/json")
		@Secured({ "ROLE_USER", "ROLE_ADMIN" })
		public ResponseEntity<?> getCarroById(@PathVariable("id") long id) {
			CarroDTO carro = getCarroService.getCarrosById(id);		
			return ResponseEntity.ok(carro);
		}

		@ApiOperation(value = "Retorna uma lista de carros filtrando por seu tipo")
		@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Retorna a lista de carros"),
			    @ApiResponse(code = 204, message = "A lista de carros está vazia"),
			    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
			})
		@GetMapping(value = "/tipo/{tipo}", produces="application/json")
		@Secured({ "ROLE_USER", "ROLE_ADMIN" })
		public ResponseEntity<?> getCarrosByTipo(@PathVariable("tipo") String tipo) {
			List<CarroDTO> carros = getCarroService.getCarrosByTipo(tipo);
			
					return ResponseEntity.ok(carros);
		}

}


