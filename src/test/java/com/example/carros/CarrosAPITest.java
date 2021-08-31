package com.example.carros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

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
import com.example.carros.model.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {

	@Autowired
	protected TestRestTemplate rest;

	private ResponseEntity<CarroDTO> getCarro(String url) {

		// Crio uma nova requisicão usando um BasicAuth como header e lançando um get na
		// url enviada
		// como paramêtro, depois a resposta será convertida e armazenada em um
		// responseEntity
		return rest.withBasicAuth("root", "123").getForEntity(url, CarroDTO.class);
	}

	private ResponseEntity<List<CarroDTO>> getListCarros(String url) {

		// executa uma requisição http com o modelo fornecido dentro do metodo exchange,
		// a resposta é retornada como um ResponseEntity.
		// O ParameterizedTypeReference é usado para repassar informações de um tipo
		return rest.withBasicAuth("root", "123").exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CarroDTO>>() {
				});
	}
	
	private ResponseEntity<?> putCarro(String url, Carro carro) {
		 rest.withBasicAuth("root", "123").put(url, carro);
		 
		 return new ResponseEntity<>(carro, HttpStatus.OK);
	}
	
	private ResponseEntity<CarroDTO> postCarro(String url, Carro carro) {
		return rest.withBasicAuth("root", "123").postForEntity(url, carro, null);
	}

	@Test
	public void crudTest() {

		Carro carro = new Carro();
		carro.setNome("Fusca");
		carro.setTipo("classicos");

		ResponseEntity<?> response = postCarro("/api/v1/carros", carro);
		System.out.println(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		String location = "/" + response.getHeaders().get("location").get(0);
		CarroDTO carroDTO = getCarro(location).getBody();

		assertNotNull(carroDTO);
		assertEquals("Fusca", carroDTO.getNome());
		assertEquals("classicos", carroDTO.getTipo());

		Carro carroUpdate = new Carro();
		carro.setNome("Gol quadrado");
		carro.setTipo("classicos");
		
		ResponseEntity<?> responseUpdate = putCarro(location, carroUpdate);
      	assertEquals(HttpStatus.OK, responseUpdate.getStatusCode());

		rest.withBasicAuth("root", "123").delete(location);

		assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
	}

	@Test
	public void listPerType() {

		assertEquals(10, getListCarros("/api/v1/carros/tipo/classicos").getBody().size());
		assertEquals(10, getListCarros("/api/v1/carros/tipo/esportivos").getBody().size());
		assertEquals(10, getListCarros("/api/v1/carros/tipo/luxo").getBody().size());
	}
	
	@Test
	public void listPerTypeNotFound() {

		assertEquals(HttpStatus.BAD_REQUEST , getCarro("/api/v1/carros/tipo/xxx").getStatusCode());
	}

	@Test
	public void testGetOk() {

		ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
		assertEquals(HttpStatus.OK, response.getStatusCode());

		CarroDTO carro = response.getBody();
		assertEquals("Ferrari FF", carro.getNome());
	}

	@Test
	public void testGetNotFound() {

		ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11100");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}
