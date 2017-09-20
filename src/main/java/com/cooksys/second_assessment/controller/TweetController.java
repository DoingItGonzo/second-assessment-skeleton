package com.cooksys.second_assessment.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.dto.HashTagDto;
import com.cooksys.second_assessment.dto.TweetDto;
import com.cooksys.second_assessment.service.TweetService;

@RestController
@RequestMapping("tweets")
public class TweetController {
	
	private TweetService tweetService;
	
	public TweetController(TweetService tweetService){
		this.tweetService = tweetService;
	}
	
	
	@GetMapping("tweets/{id}")
	public TweetDto getTweet(@PathVariable Integer id){
		return tweetService.getTweet(id);
	}
	
	@GetMapping("tweets/{id}/tags")
	public HashTagDto getHashTag(@PathVariable Integer id){
		return tweetService.getHashTag(id);
	}
	
	@GetMapping("tweets/{id}/likes")
	public List<ClientDto> getClientsWhoLiked(@PathVariable Integer id){
		return tweetService.getClientsWhoLiked(id);
	}
	
	@PostMapping
	public TweetDto postTweet(@RequestBody TweetDto tweetDto){
		return tweetService.postTweet(tweetDto);
	}
	
	@DeleteMapping("tweets/{id}")
	public TweetDto deleteTweet(@PathVariable Integer id) {
		return tweetService.deleteTweet(id);
	}

}
