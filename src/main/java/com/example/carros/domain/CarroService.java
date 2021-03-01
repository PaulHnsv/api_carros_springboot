package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;

public class CarroService {
	
public List<Carro> getCarros(){
	List<Carro> carros = new ArrayList<>();
	
	carros.add(new Carro(1l, "Fusca"));
	carros.add(new Carro(2l, "Brasilia"));
	carros.add(new Carro(3l, "Chevette"));
	
	return carros;
}


}
