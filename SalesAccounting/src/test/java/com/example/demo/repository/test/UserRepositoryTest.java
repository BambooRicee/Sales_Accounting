package com.example.demo.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {
   
   @Autowired 
   private UserRepository userRepository;
   
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
   public void givenUserObject_whenSave_thenReturnSavedUser(){
       
	 	user = User.builder()
	 		      .username("Лиза")
	               .password("87654321")
	               .email("lizap@gmail.com")
	               .build();

       User savedUser = userRepository.save(user);
       assertThat(savedUser).isNotNull();
       assertThat(savedUser.getId()).isGreaterThan(0);
       
   }
   
   
   
   
   @Test
   public void givenUserObject_whenFindById_thenReturnUserObject(){

      userRepository.save(user);
      
      User userDB = userRepository.findById(user.getId()).get();
      
      assertThat(userDB).isNotNull();
   }
   
   
   @Test
   public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser(){
       
       userRepository.save(user);

      User savedUser = userRepository.findById(user.getId()).get();
      savedUser.setId(1L);
      savedUser.setUsername("Лиза");
      savedUser.setPassword("87654321");
      savedUser.setEmail("lizap@gmail.com");

      User updatedUser =  userRepository.save(savedUser);

      assertThat(updatedUser.getId()).isEqualTo(1L);
      assertThat(updatedUser.getUsername()).isEqualTo("Лиза");
      assertThat(updatedUser.getPassword()).isEqualTo("87654321");
      assertThat(updatedUser.getEmail()).isEqualTo("lizap@gmail.com");
   }
   
   
   @Test
   public void givenUserObject_whenDelete_thenRemoveUser(){
       
       userRepository.save(user);

       userRepository.deleteById(user.getId());
       Optional<User> userOptional = userRepository.findById(user.getId());

       assertThat(userOptional).isEmpty();
   }

}