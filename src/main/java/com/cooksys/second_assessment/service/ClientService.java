package com.cooksys.second_assessment.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.second_assessment.entity.Client;
import com.cooksys.second_assessment.mapper.ClientMapper;
import com.cooksys.second_assessment.repository.ClientRepository;

@Service
public class ClientService {
	
	private ClientMapper clientMapper;
	private ClientRepository clientRepository;
	
	public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	public Client createClient(Client client) {
		return clientRepository.save(client);
	}

	public List<Client> getAll() {
		return clientRepository.findByIsActiveTrue();
	}

	public Boolean getClientExists(String username) {
		return (clientRepository.findByCredentialsUsernameAndIsActiveTrue(username) != null) ? true: false;
	}

	public Boolean getClientAvailable(String username) {
		return (clientRepository.findByCredentialsUsername(username) == null) ? true: false;
	}

	public Client getClient(String username) {
		return (clientRepository.findByCredentialsUsernameAndIsActiveTrue(username));
	}

	//ERROR
	//MOTHAH FUCKIN'
	//HANDLING
	public Client patchClient(String username, Client clientDto) {
		Client client = clientRepository.findByCredentialsUsername(username);
		client.setProfile(clientDto.getProfile());
		return clientRepository.save(client);
	}

	public Client deleteClient(String username) {
		Client client = clientRepository.findByCredentialsUsername(username);
		client.setIsActive(false);
		return clientRepository.save(client);

	}

}
