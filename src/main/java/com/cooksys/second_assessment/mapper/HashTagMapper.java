package com.cooksys.second_assessment.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.second_assessment.dto.HashTagDto;
import com.cooksys.second_assessment.entity.HashTag;

@Mapper(componentModel="spring")
public interface HashTagMapper {
	
	HashTag fromDto(HashTagDto hashTagDto);
	
	HashTagDto toDto(HashTag hashTag);
	
	List<HashTag> fromDtoList(List<HashTagDto> hashTagDtos);
	
	List<HashTagDto> fromHashTagList(List<HashTag> hashTagList);

}
