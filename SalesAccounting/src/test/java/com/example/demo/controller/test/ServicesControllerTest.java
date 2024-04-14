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
public class ServicesControllerTest {
	
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
	public void testServices() throws Exception {
		mockMvc.perform(get("/services"))
			.andExpect(status().isOk())
			.andExpect(view().name("services"))
			.andExpect(content().string(containsString("Сервисы")));
	}
	
	
	@Test
	@WithMockUser(roles = "USER")
	public void testServicesAdd() throws Exception {
		mockMvc.perform(get("/services/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("servicesAdd"))
			.andExpect(content().string(containsString("Добавить сервис")));
	}

}
