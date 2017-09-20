package com.cooksys.second_assessment.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	private ClientService clientService;
	
	public ClientController(ClientService ClientService) {
		this.clientService = ClientService;
	}
	
	@GetMapping
	public List<ClientDto> getClients() {
		return clientService.getAll();
	}
	
	@GetMapping("users/@{username}")
	public ClientDto getClient(@PathVariable String username, HttpServletResponse response) {
		ClientDto client = clientService.getClient(username);
		
		if (client != null) response.setStatus(HttpServletResponse.SC_FOUND);
		else response.setStatus(HttpServletResponse.SC_NOT_FOUND); 
		return client;
	}
	
	@GetMapping("validate/username/exists/@{username}")
	public Boolean getClientExists(@PathVariable String username) {
		return clientService.getClientExists(username);
	}
	
	@GetMapping("validate/username/available/@{username}")
	public Boolean getClientAvailable(@PathVariable String username) {
		return clientService.getClientAvailable(username);
	}
	
	@PostMapping
	public ClientDto createClient(@RequestBody ClientDto clientDto, HttpServletResponse response) {
		ClientDto dto = clientService.createClient(clientDto);
		
		if (dto != null) response.setStatus(HttpServletResponse.SC_CREATED); 
		else response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return dto;
	}
	
	@PatchMapping("users/@{username}")
	public ClientDto patchClient(@PathVariable String username, @RequestBody ClientDto clientDto) {
		return clientService.patchClient(username, clientDto);
	}
	
	@DeleteMapping("users/@{username}")
	public ClientDto deleteClient(@PathVariable String username, HttpServletResponse response) {
		ClientDto dto = clientService.deleteClient(username);
		
		if (dto != null) response.setStatus(HttpServletResponse.SC_ACCEPTED);
		else response.setStatus(HttpServletResponse.SC_NOT_FOUND); 
		return dto;
	}
	
}