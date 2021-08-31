package com.example.carros.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.service.userService.GetUserDetailsServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	GetUserDetailsServiceImpl getUserDetailsServiceImpl;

	//retorna os dados do usuário logado na aplicação
	@GetMapping("/userInfo")
	public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {
		return getUserDetailsServiceImpl.loadUserByUsername(user.getUsername());
	}
}
