package com.example.demo.controller.test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ServicesRepository;
import com.example.demo.repository.UserRepository;

@WebMvcTest
public class ClientControllerTest {
	
	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean 
	private PaymentRepository paymentRepository;
	
	@MockBean
	private ClientRepository clientRepository;
	
	@MockBean
	private ServicesRepository servicesRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	
	@Test
	@WithMockUser(roles = "USER")
	public void testClient() throws Exception {
		mockMvc.perform(get("/client"))
			.andExpect(status().isOk())
			.andExpect(view().name("client"))
			.andExpect(content().string(containsString("Клиенты")));
	}
	
	
	@Test
	@WithMockUser(roles = "USER")
	public void testClientAdd() throws Exception {
		mockMvc.perform(get("/client/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("clientAdd"))
			.andExpect(content().string(containsString("Добавить клиента")));
	}

}
