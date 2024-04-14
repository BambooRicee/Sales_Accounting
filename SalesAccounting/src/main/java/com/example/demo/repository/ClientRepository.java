package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{

	Client findByName(String name);
	Client findByEmailClient(String emailClient);
	Iterable<Client> findAllByOrderById();
	Iterable<Client> findAllByOrderByName();
}