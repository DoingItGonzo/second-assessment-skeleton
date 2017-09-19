package com.cooksys.second_assessment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.second_assessment.entity.Client;
import com.cooksys.second_assessment.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	private ClientService clientService;
	
	public ClientController(ClientService ClientService) {
		this.clientService = ClientService;
	}
	
	@GetMapping
	public List<Client> getClients() {
		return clientService.getAll();
	}
	
	@GetMapping("users/@{username}")
	public Client getClient(@PathVariable String username) {
		return clientService.getClient(username);
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
	public Client createClient(@RequestBody Client client) {
		return clientService.createClient(client);
	}
	
	@PatchMapping("users/@{username}")
	public Client patchClient(@PathVariable String username, @RequestBody Client clientDto) {
		return clientService.patchClient(username, clientDto);
	}
	
	@DeleteMapping("users/@{username}")
	public Client deleteClient(@PathVariable String username) {
		return clientService.deleteClient(username);
	}
	
}
