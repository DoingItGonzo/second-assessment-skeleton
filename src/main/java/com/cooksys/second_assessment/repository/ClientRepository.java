package com.cooksys.second_assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.second_assessment.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	List<Client> findByIsActiveTrue();

	Client findByCredentialsUsernameAndIsActiveTrue(String username);
	
	Client findByCredentialsUsername(String username);
}