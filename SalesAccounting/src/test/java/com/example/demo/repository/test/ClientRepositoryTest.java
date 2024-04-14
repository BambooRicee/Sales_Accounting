package com.example.demo.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;


@DataJpaTest
public class ClientRepositoryTest {
   
   @Autowired 
   private ClientRepository clientRepository;
   
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
   public void givenClientrObject_whenSave_thenReturnSavedClient(){
       
	 	client = Client.builder()
	 			.name("Передерий Елизавета Александровна")
	            .emailClient("lizaperedery@mail.ru")
	            .phone("89181234523")
	            .status("активен")
	            .build();

       Client savedClient = clientRepository.save(client);
       assertThat(savedClient).isNotNull();
       assertThat(savedClient.getId()).isGreaterThan(0);
       
   }
   
   
   @Test
   public void givenClientObject_whenFindById_thenReturnClientObject(){

	   clientRepository.save(client);
      
	  Client clientDB = clientRepository.findById(client.getId()).get();
      
      assertThat(clientDB).isNotNull();
   }
   
   
   @Test
   public void givenClientObject_whenUpdateClient_thenReturnUpdatedClient(){
       
	   clientRepository.save(client);

	  Client savedClient = clientRepository.findById(client.getId()).get();
      savedClient.setId(1L);
      savedClient.setName("Рис");
      savedClient.setEmailClient("bamboo@mail.ru");
      savedClient.setPhone("89183456789");
      savedClient.setStatus("не активен");

      Client updatedClient =  clientRepository.save(savedClient);

      assertThat(updatedClient.getId()).isEqualTo(1L);
      assertThat(updatedClient.getName()).isEqualTo("Рис");
      assertThat(updatedClient.getEmailClient()).isEqualTo("bamboo@mail.ru");
      assertThat(updatedClient.getPhone()).isEqualTo("89183456789");
      assertThat(updatedClient.getStatus()).isEqualTo("не активен");
   }
   
   
   @Test
   public void givenClientObject_whenDelete_thenRemoveClient(){
       
	   clientRepository.save(client);

	   clientRepository.deleteById(client.getId());
       Optional<Client> clientOptional = clientRepository.findById(client.getId());

       assertThat(clientOptional).isEmpty();
   }

}