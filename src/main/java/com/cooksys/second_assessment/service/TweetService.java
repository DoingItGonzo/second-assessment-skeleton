package com.cooksys.second_assessment.service;

import java.util.List;

import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.dto.HashTagDto;
import com.cooksys.second_assessment.dto.TweetDto;
import com.cooksys.second_assessment.mapper.ClientMapper;
import com.cooksys.second_assessment.repository.TweetRepository;

public class TweetService {
	
	private ClientMapper clientMapper;
	private TweetRepository tweetRepository;
	
	public TweetService(TweetRepository tweetRepository, ClientMapper clientMapper) {
		this.tweetRepository = tweetRepository;
	}

	public TweetDto getTweet(Integer id) {
		return tweetMapper.toDto(tweetRepository.findOneById(id));
	}

	public TweetDto deleteTweet(Integer id) {
		tweetRepository.findOneById(id).setIsActive(false);
		return tweetMapper.toDto(tweetRepository.findOneById(id));
	}

	public HashTagDto getHashTag(Integer id) {
		return hashTagMapper.toDto(tweetRepository.findOneById(id).getHashTag());
	}

	public List<ClientDto> getClientsWhoLiked(Integer id) {
		return clientMapper.fromClientList(tweetRepository.findOneById(id).getClientsWhoLiked());
	}

	public TweetDto postTweet(TweetDto tweetDto) {
		return tweetMapper.toDto(tweetRepository.save(tweetDto));
		return null;
	}

}
