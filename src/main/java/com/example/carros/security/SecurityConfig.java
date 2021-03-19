package com.example.carros.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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

    	//encriptador de senhas, obrigatório
    	BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
    	
        auth
   	  	.inMemoryAuthentication().passwordEncoder(enconder)
   	  	.withUser("root").password(enconder.encode("root")).roles("ADMIN", "USER")
   	  	.and()
   	  	.withUser("user").password(enconder.encode("user")).roles("USER");
    }
}