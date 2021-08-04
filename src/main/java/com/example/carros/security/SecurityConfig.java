package com.example.carros.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//Qualifier serve para diferenciar qual bean será utilizada na injeção de dependências
	@Qualifier("userDetailsService")
	@Autowired
	private UserDetailsService userDetailsService;

	//configuração geral do SpringSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and().httpBasic()
        	.and().csrf().disable();
    }
    
    //configurando perfis de usuário
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    	//a senha ficará em hashcode
    	//encriptador de senhas, obrigatório
    	BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
    	
    	//implementação do nosso service responsável por criar os usuários
    	auth.userDetailsService(userDetailsService).passwordEncoder(enconder);
    	
    }
}