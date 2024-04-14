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

import com.example.demo.model.Services;
import com.example.demo.repository.ServicesRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/services")
public class ServicesController {
	
	@Autowired
	private ServicesRepository servicesRepository;
	private Services service;
	
	
	@GetMapping
	public String services(Model model) {
		Iterable<Services> services = servicesRepository.findAllByOrderByNameServices();
		model.addAttribute("services", services);
		return "services";
	}
	
	@GetMapping(value = "/add")
	public String newService(Model model) {
		Services services = new Services();
		model.addAttribute("services", services);
		return "servicesAdd";
	}
	
	@GetMapping(value = "/edit")
	public String servicesEdit(Model model, @RequestParam Long id) {
		Services services = servicesRepository.findById(id).orElseThrow();
		service = services;
		model.addAttribute("services", services);
		return "servicesEdit";
	}
	
	
	
	@PostMapping(value = "/add")
	public String saveNewServices(@Valid @ModelAttribute Services services, BindingResult 
			bindingResult,String passwordRepeat){
		
		if(bindingResult.hasErrors()) {
			return "servicesAdd";
		}
		    servicesRepository.save(services);
			return "redirect:/services";
		}
		
	
	@PostMapping(value = "/edit")
	public String saveEditServices(@Valid @ModelAttribute Services services, 
			BindingResult bindingResult, String passwordRepeat){
		
		if (bindingResult.hasErrors()) {
			return "servicesEdit";
		}
		else {
			servicesRepository.save(services);
			return "redirect:/services";
		}
	}
	
	@PostMapping
	public String deleteServices(@ModelAttribute Services services){
		Services servicesDelete = servicesRepository.findById(services.getId()).orElseThrow();
		servicesRepository.delete(servicesDelete);
		return "redirect:/services";
	}
	
}













