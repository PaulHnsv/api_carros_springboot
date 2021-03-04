package com.example.carros.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	//sem mapeamento ele vai pegar o padrão da classe
//	@GetMapping
//	public String get() {
//		return "get Spring Boot";
//	}
	
	
	//request e path params
//	@GetMapping("/login")
//	public String login(@RequestParam("login") String login,@RequestParam("senha") String senha) {
//		return "Login: " + login + ", Senha: " + senha;
//	}
	
	//path params
//	@GetMapping("/login/{login}/senha/{senha}")
//	public String login(@PathVariable("login") String login, @PathVariable("senha") String senha) {
//		return "Login: " + login + ", Senha: " + senha;
//	}
//	
	//path params
//	@GetMapping("/carros/{id}")
//	public String getCarrosById(@PathVariable("id") long id) {
//		return "Carro by id: " + id;
//	}
//
//	@PostMapping
//	public String post() {
//		return "post Spring Boot";
//	}
//	
//	@PutMapping
//	public String put() {
//		return "put Spring Boot";
//	} 
//	
//	@DeleteMapping
//	public String delete() {
//		return "delete Spring Boot";
//	}
	
	//exemplo de post com parametros
//	@PostMapping("/carros")
//	public String getCarrosById(@RequestParam("login") String login, @RequestParam("senha") String senha) {
//		return "Login: " + login + ", Senha: " + senha;
//	}
	
	@GetMapping
	public String get() {
		return "API dos Carros";
	}
}
