package com.example.carros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.carros.model.Carro;
import com.example.carros.service.CarroService;
import com.examples.carros.dto.CarroDTO;

@SpringBootTest
class CarroServiceTest {

	@Autowired
	private CarroService carroService;

	@Test
	public void crudTest() {

		// Create carro
		Carro carro = new Carro();
		carro.setNome("Fusca");
		carro.setTipo("classicos");

		CarroDTO c = carroService.saveCarro(carro);

		// Read carro
		long id = c.getId();
		assertNotNull(id);

		Optional<CarroDTO> getcarro = carroService.getCarrosById(id);
		assertNotNull(getcarro.isPresent());

		c = getcarro.get();
		assertEquals("Fusca", c.getNome());
		assertEquals("classicos", c.getTipo());

		// Update carro
		Carro carroUpdate = new Carro();
		carroUpdate.setNome("Gol Quadrado");
		carroUpdate.setTipo("esportivos");

		CarroDTO c2 = carroService.updateCarro(carroUpdate, id);
		assertEquals(carroUpdate.getNome(), c2.getNome());
		assertEquals(carroUpdate.getTipo(), c2.getTipo());

		// Delete carro
		carroService.deleteCarro(id);
		assertFalse(carroService.getCarrosById(id).isPresent());
	}

	@Test
	public void testLista() {
		List<CarroDTO> carros = carroService.getCarros();
		assertEquals(30, carros.size());
	}

	@Test
	public void testListaPorTipo() {

		assertEquals(10, carroService.getCarrosByTipo("classicos").size());
		assertEquals(10, carroService.getCarrosByTipo("esportivos").size());
		assertEquals(10, carroService.getCarrosByTipo("luxo").size());
		assertEquals(0, carroService.getCarrosByTipo("populares").size());
	}

}
