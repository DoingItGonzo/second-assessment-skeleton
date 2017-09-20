package com.cooksys.second_assessment.service;


import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.entity.Client;
import com.cooksys.second_assessment.mapper.ClientMapper;
import com.cooksys.second_assessment.repository.ClientRepository;

@Service
public class ClientService {
	//
	//
	//
	//
	/// @creationTimestamp
	// @Make them impossible to update
	//
	// Test controllers for default reesponse codes
	// 
	//
	//
	//
	//
	private ClientMapper clientMapper;
	private ClientRepository clientRepository;
	
	public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	public ClientDto createClient(ClientDto clientDto) {

		Client client = clientRepository.findByCredentialsUsername(clientDto.getCredentials().getUsername());
		if (client != null) {
			if (!client.getIsActive()) {
				client.setIsActive(true);
				return clientMapper.toDto(clientRepository.save(client));
			} else
				return null;
		} else {
			return clientMapper.toDto(clientRepository.save(clientMapper.fromDto(clientDto)));
		}
	}

	public List<ClientDto> getAll() {  
		return clientMapper.fromClientList(clientRepository.findByIsActiveTrue());
	}

	public Boolean getClientExists(String username) {
		return (clientRepository.findByCredentialsUsernameAndIsActiveTrue(username) != null) ? true: false;
	}

	public Boolean getClientAvailable(String username) {
		return (clientRepository.findByCredentialsUsername(username) == null) ? true: false;
	}

	public ClientDto getClient(String username) {
		return clientMapper.toDto(clientRepository.findByCredentialsUsernameAndIsActiveTrue(username));
	}

	public ClientDto patchClient(String username, ClientDto clientDto) {
		Client client = clientRepository.findByCredentialsUsername(username);
		client.setProfile(clientDto.getProfile());
		client.getCredentials().setPassword(clientDto.getCredentials().getPassword());
		return clientMapper.toDto(clientRepository.save(client));
	}

	public ClientDto deleteClient(String username) {
		Client client = clientRepository.findByCredentialsUsername(username);
		if (client == null) 
			return null;
		else 
			client.setIsActive(false);
			return clientMapper.toDto(clientRepository.save(client));

	}

	public void follow(String followedClient, ClientDto follower) {
		Client client = clientRepository.findByCredentialsUsername(followedClient);
		client.getFollowers().add(clientRepository.findByCredentialsUsername(follower.getCredentials().getUsername()));
	}
	
	public List<ClientDto> getFollowers(String username){
		return clientMapper.fromClientList(clientRepository.findByCredentialsUsername(username).getFollowers());

	}

}
