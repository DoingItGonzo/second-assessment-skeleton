package com.cooksys.second_assessment.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.second_assessment.dto.TweetDto;
import com.cooksys.second_assessment.entity.Tweet;

@Mapper(componentModel="spring")
public interface TweetMapper {
	
	TweetDto toDto(Tweet tweet);
	
	Tweet fromDto(TweetDto tweetDto);
	
	List<TweetDto> fromTweetList(List<Tweet> tweet);
	
	List<Tweet> fromDtoList(List<TweetDto> tweetDto);

}
