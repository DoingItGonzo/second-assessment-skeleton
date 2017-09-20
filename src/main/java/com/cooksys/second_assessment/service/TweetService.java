package com.cooksys.second_assessment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.dto.HashTagDto;
import com.cooksys.second_assessment.dto.TweetDto;
import com.cooksys.second_assessment.entity.Tweet;
import com.cooksys.second_assessment.mapper.ClientMapper;
import com.cooksys.second_assessment.mapper.TweetMapper;
import com.cooksys.second_assessment.repository.TweetRepository;

@Service
public class TweetService {
	
	private ClientMapper clientMapper;
	private TweetRepository tweetRepository;
	private TweetMapper tweetMapper;
	
	public TweetService(TweetRepository tweetRepository, ClientMapper clientMapper, TweetMapper tweetMapper) {
		this.tweetRepository = tweetRepository;
		this.clientMapper = clientMapper;
		this.tweetMapper = tweetMapper;
	}

	public TweetDto getTweet(Integer id) {
		return tweetMapper.toDto(tweetRepository.findOneById(id));
	}

	//Return without updated field??
	public TweetDto deleteTweet(Integer id) {
		Tweet tweet = tweetRepository.findOneById(id);
		tweet.setIsActive(false);
		return tweetMapper.toDto(tweetRepository.save(tweet));
	}

	public HashTagDto getHashTag(Integer id) {
//		return hashTagMapper.toDto(tweetRepository.findOneById(id).getHashTag());
		return null;
	}

	public List<ClientDto> getClientsWhoLiked(Integer id) {
		return clientMapper.fromClientList(tweetRepository.findOneById(id).getClientsWhoLiked());
	}

	public TweetDto postTweet(TweetDto tweetDto) {
		Tweet tweet = new Tweet();
		tweet.setContent(tweetDto.getContent());
		tweet.setCredentials(tweetDto.getCredentials());
		return tweetMapper.toDto(tweetRepository.save(tweet));
	}

}
