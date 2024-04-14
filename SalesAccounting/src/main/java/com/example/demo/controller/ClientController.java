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
import com.example.demo.model.Client;
import com.example.demo.model.Payment;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PaymentRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	private String name1;

	@GetMapping
	public String client(Model model) {
		Iterable<Client> client = clientRepository.findAllByOrderByName();
		Iterable<Payment> payment = paymentRepository.findAll();
		model.addAttribute("payment", payment);
		model.addAttribute("client", client);
		return "client";
	}
	
	
	@GetMapping(value = "/add")
	public String newClient(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "clientAdd";
	}
	
	
	@GetMapping(value = "/edit")
	public String clientEdit(Model model, @RequestParam Long id) {
		Client client = clientRepository.findById(id).orElseThrow();
		name1 = client.getName();
		model.addAttribute("client", client);
		return "clientEdit";
	}

	@PostMapping(value = "/add")
	public String saveNewClient(@Valid @ModelAttribute Client client, BindingResult 
			bindingResult,String passwordRepeat){
		
		if(bindingResult.hasErrors()) {
			return "clientAdd";
		}
		    clientRepository.save(client);
			return "redirect:/client";
		}
		
	
	@PostMapping(value = "/edit")
	public String saveEditClient(@Valid @ModelAttribute Client client, 
			BindingResult bindingResult, String passwordRepeat){
		
		if (bindingResult.hasErrors()) {
			return "clientEdit";
		}
		else {
			clientRepository.save(client);
			return "redirect:/client";
		}
	}
	
	@PostMapping
	public String deleteClient(@ModelAttribute Client client){
		Client clientDelete = clientRepository.findById(client.getId()).orElseThrow();
		clientRepository.delete(clientDelete);
		return "redirect:/client";
	}
}
