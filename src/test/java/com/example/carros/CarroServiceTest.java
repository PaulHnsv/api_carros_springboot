package com.example.carros;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.carros.exception.ObjectNotFoundException;
import com.example.carros.model.Carro;
import com.example.carros.model.dto.CarroDTO;
import com.example.carros.service.carroService.CarroService;

@SpringBootTest
class CarroServiceTest {

	@Autowired
	private CarroService carroService;

	@Test
	public void crudTest() {

		Carro carro = new Carro();
		carro.setNome("Fusca");
		carro.setTipo("classicos");

		CarroDTO c = carroService.saveCarro(carro);
		assertNotNull(c);

		long id = c.getId();
		assertNotNull(id);

		c = carroService.getCarrosById(id);
		
		assertNotNull(c);
		assertEquals("Fusca", c.getNome());
		assertEquals("classicos", c.getTipo());

		Carro carroUpdate = new Carro();
		carroUpdate.setNome("Gol Quadrado");
		carroUpdate.setTipo("esportivos");

		CarroDTO c2 = carroService.updateCarro(carroUpdate, id);
		assertEquals(carroUpdate.getNome(), c2.getNome());
		assertEquals(carroUpdate.getTipo(), c2.getTipo());

		carroService.deleteCarro(id);
		try {
		assertNull(carroService.getCarrosById(id));
		fail("O carro não foi excluído");
		}catch(ObjectNotFoundException ex) {
			System.out.println("O carro foi deletado sucesso");
		}
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
	}

}
