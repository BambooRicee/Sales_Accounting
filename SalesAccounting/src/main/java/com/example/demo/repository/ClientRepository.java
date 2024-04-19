package com.example.demo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Client;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{

	Client findByName(String name);
	Client findByEmailClient(String emailClient);
	Iterable<Client> findAllByOrderById();
	Iterable<Client> findAllByOrderByName();
}