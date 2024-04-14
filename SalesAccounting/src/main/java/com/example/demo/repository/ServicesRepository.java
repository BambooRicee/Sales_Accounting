package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.Services;

public interface ServicesRepository extends JpaRepository<Services, Long>{
	Iterable<Services> findAllByOrderByNameServices();
	Services findByNameServices(String nameServices);
	
}
