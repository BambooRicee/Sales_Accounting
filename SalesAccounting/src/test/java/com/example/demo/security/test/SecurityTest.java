package com.example.demo.security.test;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.example.demo.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

	@Autowired 
	private WebApplicationContext context;
	
	@Autowired 
	private MockMvc mockMvcAnonymous;
	
	@Autowired 
	private MockMvc mockMvcUser;
	
	@MockBean
	private UserRepository userRepository;

	
	@BeforeEach()
	public void setup(){
		
		mockMvcUser = MockMvcBuilders
	    		.webAppContextSetup(context)
	    		.build();
	    
		mockMvcAnonymous = MockMvcBuilders
	            .webAppContextSetup(context)
	            .apply(springSecurity())  
	            .build();
	}
   
    @Test
    void givenUserIsAnonymous_whenGetHomePage_thenOk() throws Exception {
    	mockMvcAnonymous.perform(get("/"))
    		.andExpect(status().isOk());
    }
    
    
    @Test
    void givenUserIsAnonymous_whenGetLoginPage_thenOk() throws Exception {
    	mockMvcAnonymous.perform(get("/login"))
    		.andExpect(status().isOk());
    }
    
    
    @Test
    void givenUserIsAnonymous_whenGetRegisterPage_thenOk() throws Exception {
    	mockMvcAnonymous.perform(get("/register"))
    		.andExpect(status().isOk());
    }
    
    
    @Test
    public void givenUserIsAnonymous_whenGetServicesPage_thenRedirection() throws Exception {
        mockMvcAnonymous.perform(get("/services"))
            .andExpect(status().is3xxRedirection());
    }
    
    
    @Test
    public void givenUserIsAnonymous_whenGetClientPage_thenRedirection() throws Exception {
        mockMvcAnonymous.perform(get("/client"))
            .andExpect(status().is3xxRedirection());
    }
    
    
    @Test
    public void givenUserIsAnonymous_whenGetPaymentPage_thenRedirection() throws Exception {
        mockMvcAnonymous.perform(get("/payment"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "USER")
    void givenUserIsAuthorized_whenGetServicesPage_thenOk() throws Exception {
        mockMvcUser.perform(get("/services"))
                .andExpect(status().isOk());
    }

    
    @Test
    @WithMockUser(roles = "USER")
    void givenUserIsAuthorized_whenGetPaymentPage_thenOk() throws Exception {
        mockMvcUser.perform(get("/payment"))
                .andExpect(status().isOk());
    }

    
    @Test
    @WithMockUser(roles = "USER")
    void givenUserIsAuthorized_whenGetClientPage_thenOk() throws Exception {
        mockMvcUser.perform(get("/client"))
                .andExpect(status().isOk());
    }
   
    
}
















