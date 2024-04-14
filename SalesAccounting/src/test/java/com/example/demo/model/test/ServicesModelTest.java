package com.example.demo.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Services;



@SpringBootTest
public class ServicesModelTest {

	@InjectMocks
	private Services services;
	
	@BeforeEach
    void setUp() {
    	services = Services.builder()
    			.id(1L)
                .nameServices("Замена аккумулятора")
                .build();
    }
	
	
    @Test
    void testServicesId() {
        assertEquals(1L, services.getId());
    }
    
    
    @Test
    void testServicesNameServices() {
        assertEquals("Замена аккумулятора", services.getNameServices());
    }

}
