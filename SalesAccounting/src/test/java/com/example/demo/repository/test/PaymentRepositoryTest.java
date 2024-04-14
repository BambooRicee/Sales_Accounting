package com.example.demo.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Client;
import com.example.demo.model.Payment;
import com.example.demo.model.Services;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ServicesRepository;

@DataJpaTest
public class PaymentRepositoryTest {
	
	@Autowired 
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ServicesRepository servicesRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@InjectMocks
	private Payment payment;
	
	private Client client;
	private Services services;
	
    @BeforeEach
    void setUp() {
    	
    	client = Client.builder()
    			.name("Передерий Елизавета Александровна")
	            .emailClient("lizaperedery@mail.ru")
	            .phone("89181234523")
	            .status("активен")
	            .build();
    	
    	services = Services.builder()
                .nameServices("Замена аккумулятора")
                .build();
    	
    	servicesRepository.save(services);
    	clientRepository.save(client);
    	
    	payment = Payment.builder()
    			.client(client)
    			.services(services)
                .build();
    }
    
    @Test
    public void givenPaymentObject_whenSave_thenReturnSavedPayment(){
    	
    	payment = Payment.builder()
    			.client(client)
    			.services(services)
                .build();

    	Payment savedPayment = paymentRepository.save(payment);
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getId()).isGreaterThan(0);
        
    }
    
    
    @Test
    public void givePaymentObject_whenFindById_thenReturnPaymentObject(){

    	paymentRepository.save(payment);
    	
    	Payment paymentDB = paymentRepository.findById(payment.getId()).get();
    	
        assertThat(paymentDB).isNotNull();
    }
    
    
    @Test
    public void givenPaymentObject_whenUpdatePayment_thenReturnUpdatedPayment(){

    	paymentRepository.save(payment);

    	Payment savedPayment = paymentRepository.findById(payment.getId()).get();
    	savedPayment.setId(1L);
    	savedPayment.setClient(client);
    	savedPayment.setServices(services);
    	Payment updatedPayment =  paymentRepository.save(savedPayment);

        assertThat(updatedPayment.getId()).isEqualTo(1L);
        assertThat(updatedPayment.getServices()).isEqualTo(services);
        assertThat(updatedPayment.getClient()).isEqualTo(client);
    }
    
    @Test
    public void givenPaymentObject_whenDelete_thenRemovePayment(){

    	paymentRepository.save(payment);

    	paymentRepository.deleteById(payment.getId());
        Optional<Payment> paymentOptional = paymentRepository.findById(payment.getId());

        assertThat(paymentOptional).isEmpty();
    }
    
}







