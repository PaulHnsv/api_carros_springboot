package com.example.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carros.model.User;

public interface UserRepository extends JpaRepository<User,  Long>{

	User findByLogin(String login);
}
