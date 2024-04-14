package com.example.demo.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Client;
import com.example.demo.model.Payment;
import com.example.demo.model.Services;

@SpringBootTest
public class PaymentModelTest {

	@InjectMocks
    private Payment payment;
	
	private Client client;
	private Services services;

    @BeforeEach
    void setUp() {
    	
    	client = Client.builder().build();
    	services = Services.builder().build();
    	
    	payment = Payment.builder()
    			.id(1L)
    			.services(services)
    			.client(client)
                .build();
    }
   
    @Test
    void testPaymentId() {
        assertEquals(1L, payment.getId());
    }
   
    @Test
    void testPaymentClient() {
        assertEquals(client, payment.getClient());
    }
    
    
    @Test
    void testPaymentServices() {
        assertEquals(services, payment.getServices());
    }
    
}










