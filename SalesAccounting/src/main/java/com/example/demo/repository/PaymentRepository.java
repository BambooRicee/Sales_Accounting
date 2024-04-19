package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Payment;
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{
	@Query(value = "SELECT payment.* FROM payment "
			+ "JOIN services ON payment.services_id = services.id "
			+ "ORDER BY services.name_services,payment.client_id", nativeQuery = true)
	Iterable<Payment> findAllByOrderByServices();
	@Query(value = "SELECT payment.* FROM payment\r\n"
			+ "JOIN client ON payment.client_id = client.id\r\n"
			+ "JOIN services ON payment.services_id = services.id\r\n"
			+ "ORDER BY client.name, services.name_services", nativeQuery = true)
	Iterable<Payment> findAllByOrderByClient();
	@Query(value = "SELECT * FROM payment "
			+ "WHERE  client_id = ? AND services_id = ?", nativeQuery = true)
	Payment findByClientAndServices(Long clientId, Long servicesId);
	Iterable<Payment> findAllByClientId(Long clientId);
	
}
