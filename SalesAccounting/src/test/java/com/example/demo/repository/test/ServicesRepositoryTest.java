package com.example.demo.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Services;
import com.example.demo.repository.ServicesRepository;


@DataJpaTest
public class ServicesRepositoryTest {
	
	@Autowired 
	private ServicesRepository servicesRepository;
	
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
    public void givenServicesObject_whenSave_thenReturnSavedServices(){
    	
    	services = Services.builder()
                .nameServices("Замена аккумулятора")
                .build();

    	Services savedServices = servicesRepository.save(services);
        assertThat(savedServices).isNotNull();
        assertThat(savedServices.getId()).isGreaterThan(0);
        
    }
    
    
    @Test
    public void givenServicesObject_whenFindById_thenReturnServicesObject(){

    	servicesRepository.save(services);
       
       Services servicesDB = servicesRepository.findById(services.getId()).get();
       
       assertThat(servicesDB).isNotNull();
    }
    
    
    @Test
    public void givenServicesObject_whenUpdateServices_thenReturnUpdatedServices(){
    	
    	servicesRepository.save(services);

    	Services savedServices = servicesRepository.findById(services.getId()).get();
       savedServices.setId(1L);
       savedServices.setNameServices("Ремонт блока питания проектора");

       

       Services updatedServices =  servicesRepository.save(savedServices);
       assertThat(updatedServices.getId()).isEqualTo(1L);
       assertThat(updatedServices.getNameServices()).isEqualTo("Ремонт блока питания проектора");
    }
    
    
    @Test
    public void givenServicesObject_whenDelete_thenRemoveServices(){
    	
    	servicesRepository.save(services);

    	servicesRepository.deleteById(services.getId());
    	Optional<Services> servicesOptional = servicesRepository.findById(services.getId());

    	assertThat(servicesOptional).isEmpty();
    }

}
