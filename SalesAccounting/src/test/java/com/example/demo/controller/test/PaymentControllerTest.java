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
public class PaymentControllerTest {

	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean 
	private PaymentRepository paymentRepository;
	
	@MockBean
	private ServicesRepository servicesRepository;
	
	@MockBean
	private ClientRepository clientRepository;
	
	@MockBean 
	private UserRepository userRepository;
	
	@MockBean
	private PasswordEncoder passwordEncoder;

	
	@Test
	@WithMockUser(roles = "USER")
	public void testPaymentPage() throws Exception {
		mockMvc.perform(get("/payment"))
		.andExpect(status().isOk())
		.andExpect(view().name("payment"))
		.andExpect(content().string(containsString("Учет продаж сервисов")));
	}
	
	
	@Test
	@WithMockUser(roles = "USER")
	public void testPaymentAddPage() throws Exception {
		mockMvc.perform(get("/payment/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("paymentAdd"))
		.andExpect(content().string(containsString("")));
	}
	
}









