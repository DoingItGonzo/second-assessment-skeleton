package com.cooksys.second_assessment.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.entity.Client;

@Mapper(componentModel="spring")
public interface ClientMapper {

	Client fromDto(ClientDto clientDto);
	
	ClientDto toDto(Client client);
	
	List<Client> fromDtoList(List<ClientDto> dtoList);
	
	List<ClientDto> fromClientList(List<Client> clientList);
	
}
