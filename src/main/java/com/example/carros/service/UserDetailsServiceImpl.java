package com.example.carros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.carros.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//encriptador de senhas, obrigatório se você não estiver fazendo o hash das senhas
    	//BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
    	
    	com.example.carros.model.User user = userRepository.findByLogin(username);
    	if(user == null) {
    		throw new UsernameNotFoundException("User not found");
    	}
    	
    	return User.withUsername(username).password(user.getSenha()).roles("ADMIN", "USER").build();
	}

}
