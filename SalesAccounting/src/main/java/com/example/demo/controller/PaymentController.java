package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.Payment;
import com.example.demo.model.Services;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ServicesRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ServicesRepository servicesRepository;
	@Autowired
	private ClientRepository clientRepository;
	private Payment payment1;

	@GetMapping
	public String payment(Model model) {
		Iterable<Payment> payment = paymentRepository.findAllByOrderByClient();
		model.addAttribute("payment", payment);
		return "payment";
	}
	
	@GetMapping(value = "/add")
	public String newPayment(Model model) {
		Payment payment = new Payment();
		Iterable<Services> services = servicesRepository.findAllByOrderByNameServices();
		Iterable<Client> client = clientRepository.findAllByOrderByName();
		model.addAttribute("services", services);
		model.addAttribute("client", client);
		model.addAttribute("payment", payment);
		return "paymentAdd";
	}
	
	@GetMapping(value = "/edit")
	public String paymentEdit(Model model, @RequestParam Long id) {
		Payment payment = paymentRepository.findById(id).orElseThrow();
		Iterable<Services> services = servicesRepository.findAllByOrderByNameServices();
		Iterable<Client> client = clientRepository.findAllByOrderByName();
		payment1 = payment;
		model.addAttribute("services", services);
		model.addAttribute("client", client);
		model.addAttribute("payment", payment);
		return "paymentEdit";
	}
	
	@PostMapping
	public String deletePayment(@ModelAttribute Payment payment){
		Payment paymentDelete = paymentRepository.findById(payment.getId()).orElseThrow();
		paymentRepository.delete(paymentDelete);
		return "redirect:/payment";
	}
	
	@PostMapping(value = "/add")
	public String saveNewPayment(@Valid @ModelAttribute Payment payment, BindingResult bindingResult, Model model){
		
		Iterable<Services> services = servicesRepository.findAllByOrderByNameServices();
		Iterable<Client> client = clientRepository.findAllByOrderByName();
		model.addAttribute("services", services);
		model.addAttribute("client", client);
		
		if(payment.getServices() != null && payment.getClient() != null) {
			Long services_id = payment.getServices().getId();
			Long client_id = payment.getClient().getId();
			Payment as = paymentRepository.findByClientAndServices(client_id, services_id);
		}
		
		if(bindingResult.hasErrors()) {
			return "paymentAdd";
		}
		else {
			paymentRepository.save(payment);
			return "redirect:/payment";
		}
	}
	
	
	@PostMapping(value = "/edit")
	public String saveEditPayment(@Valid @ModelAttribute Payment payment, 
			BindingResult bindingResult, Model model){
		
		Iterable<Services> services = servicesRepository.findAllByOrderByNameServices();
		Iterable<Client> client = clientRepository.findAllByOrderByName();
		model.addAttribute("services", services);
		model.addAttribute("client", client);
		
		if(payment.getServices() != null && payment.getClient() != null) {
			Long services_id = payment.getServices().getId();
			Long client_id = payment.getClient().getId();
			Payment as = paymentRepository.findByClientAndServices(client_id, services_id);
		}
		
		if(bindingResult.hasErrors()) {
			return "paymentEdit";
		}
		else {
			paymentRepository.save(payment);
			return "redirect:/payment";
		}
	}
	
}






