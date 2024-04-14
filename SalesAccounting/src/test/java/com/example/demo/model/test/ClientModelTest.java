package com.example.demo.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Client;


@SpringBootTest
public class ClientModelTest {

	@InjectMocks
    private Client client;
	
    @BeforeEach
    void setUp() {
          client = Client.builder()
    			.id(1L)
                .name("Передерий Елизавета Александровна")
                .emailClient("lizaperedery@mail.ru")
                .phone("89181234523")
                .status("активен")
                .build();
    }
    
    
    @Test
    void testClienttId() {
        assertEquals(1L, client.getId());
    }
    
    
    @Test
    void testClientName() {
        assertEquals("Передерий Елизавета Александровна", client.getName());
    }
    
    
    @Test
    void testClientEmailClient() {
        assertEquals("lizaperedery@mail.ru", client.getEmailClient());
    }
   
    @Test
    void testClientPhone() {
        assertEquals("89181234523", client.getPhone());
    }

    @Test
    void testClientStatus() {
        assertEquals("активен", client.getStatus());
    }
     
}
