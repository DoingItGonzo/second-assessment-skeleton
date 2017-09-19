package com.cooksys.second_assessment.mapper;

import org.mapstruct.Mapper;

import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.entity.Client;

@Mapper(componentModel="spring")
public interface ClientMapper {

	Client fromDto(ClientDto clientDto);
	
	ClientDto toDto(Client client);
	
}
