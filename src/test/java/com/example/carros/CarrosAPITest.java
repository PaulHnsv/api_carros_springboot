package com.example.carros;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.carros.model.Carro;
import com.example.carros.service.CarroService;
import com.examples.carros.dto.CarroDTO;
import com.google.gson.JsonObject;

import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {

	// injeção de dependências
	@Autowired
	protected TestRestTemplate rest;

	@Autowired
	private CarroService carroService;

	// metodos de test da api
	private ResponseEntity<CarroDTO> getCarro(String url) {

		// Crio uma nova requisicão usando um BasicAuth como header e lançando um get na
		// url enviada
		// como paramêtro, depois a resposta será convertida e armazenada em um
		// responseEntity
		return rest.getForEntity(url, CarroDTO.class);
	}

	private ResponseEntity<List<CarroDTO>> getListCarros(String url) {

		// executa uma requisição http com o modelo fornecido dentro do metodo exchange,
		// a resposta é retornada como um ResponseEntity.
		// O ParameterizedTypeReference é usado para repassar informações de um tipo
		// genérico
		return rest.withBasicAuth("user", "123").exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CarroDTO>>() {
				});
	}
	
	private ResponseEntity putCarro(String url, Carro carro) {
		 rest.put(url, carro);
		 
		 return new ResponseEntity(carro, HttpStatus.OK);
	}
	
	private ResponseEntity<CarroDTO> postCarro(String url, Carro carro) {
		return rest.withBasicAuth("admin", "123").postForEntity(url, carro, null);
	}

	// Crud test APICarros
	@Test
	public void crudTest() {

		// Create carro
		Carro carro = new Carro();
		carro.setNome("Fusca");
		carro.setTipo("classicos");

		ResponseEntity response = postCarro("/api/v1/carros", carro);
		System.out.println(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		// Buscar o objeto
		String location = "/" + response.getHeaders().get("location").get(0);
		CarroDTO c = getCarro(location).getBody();

		// verificar se o carro foi mesmo encontrado e comparar os campos de retorno
		assertNotNull(c);
		assertEquals("Fusca", c.getNome());
		assertEquals("classicos", c.getTipo());

		// Update carro
		Carro carroUpdate = new Carro();
		carro.setNome("Gol quadrado");
		carro.setTipo("classicos");
		
		ResponseEntity responseUpdate = putCarro(location, carroUpdate);
      	assertEquals(HttpStatus.OK, responseUpdate.getStatusCode());

		// Deletar o objeto
		rest.withBasicAuth("user", "123").delete(location);

		// Verificar se deletou
		assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
	}

	@Test
	public void listPerType() {

		// primeiro lançamos alguns testes simples para saber se os tipos de carros no
		// banco batem
		// com os dados que passamos como esperados
		assertEquals(10, getListCarros("/api/v1/carros/tipo/classicos").getBody().size());
		assertEquals(10, getListCarros("/api/v1/carros/tipo/esportivos").getBody().size());
		assertEquals(10, getListCarros("/api/v1/carros/tipo/luxo").getBody().size());

		// test comparado o http status que passamos como esperado no primeiro parametro
		// com o resultado http status do metodo get que disparamos no segundo parametro
		assertEquals(HttpStatus.NO_CONTENT, getCarro("/api/v1/carros/tipo/luxuosos").getStatusCode());
	}

	@Test
	public void testGetOk() {

		// gravação do retorno apresentado no metodo de teste em uma variavel do tipo
		// ResponseEntity,
		// depois é comparado os retornos da requisição com o resultado que esperamos
		ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// gravação do retorno do body fornecido pela variavel response em uma variavel
		// do tipo CarroDTO,
		// depois é comparado o retorno de um dos campos com o resultado que esperamos
		CarroDTO carro = response.getBody();
		assertEquals("Ferrari FF", carro.getNome());
	}

	@Test
	public void testGetNotFound() {

		// gravação do retorno apresentado no metodo de teste em uma variavel do tipo
		// ResponseEntity,
		// depois é comparado os retornos da requisição com o resultado que esperamos
		ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11100");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}
