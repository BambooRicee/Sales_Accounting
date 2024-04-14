
package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "payment")
public class Payment  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "services_id")
    @NotNull(message = "Вы должны выбрать сервис")
	private Services services;

	@ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull(message = "Вы должны выбрать клиента")
	private Client client;
	
	@Column(name = "payment_quantity")
	@Max(value = 25, message = "Максимальное значение для количества покупок - 25")
	private Integer paymentQuantity;
	
	@Column(name = "payment_amount")
	@DecimalMin(value = "0.0", inclusive = false, message = "Цена покупки должна быть больше 0")
	private Double  paymentAmount;
	}
