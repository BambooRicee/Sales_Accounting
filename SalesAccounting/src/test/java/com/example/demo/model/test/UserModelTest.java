package com.example.demo.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.User;

@SpringBootTest
public class UserModelTest {

	@InjectMocks
    private User user;
	
    @BeforeEach
    void setUp() {
    	user = User.builder()
    			.id(1L)
    		      .username("Лиза")
                  .password("87654321")
                  .email("lizap@gmail.com")
                  .build();
    }
    
    
    @Test
    void testUsertId() {
        assertEquals(1L, user.getId());
    }
    
    
    @Test
    void testUsertUsername() {
        assertEquals("Лиза", user.getUsername());
    }
    
    
    @Test
    void testUsertPassword() {
        assertEquals("87654321", user.getPassword());
    }
    
    
    @Test
    void testUsertEmail() {
        assertEquals("lizap@gmail.com", user.getEmail());
    }
     
}
