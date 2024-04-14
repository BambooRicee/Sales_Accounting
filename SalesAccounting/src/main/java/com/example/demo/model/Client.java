package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "client")
public class Client {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	@NotEmpty(message = "Введите ФИО клиента")
	private String name;
	
	
	@Column(name = "email_client")
	@NotEmpty(message = "Введите электронную почту")
	@Email(message = "Некорректный формат электронной почты")
	private String emailClient;
	
	
	@Column(name = "phone")
	@NotEmpty(message = "Введите номер телефона")
	private String phone;
	
	@Column(name = "status")
	@NotEmpty(message = "Введите статус")
	private String status;
		
	
}


