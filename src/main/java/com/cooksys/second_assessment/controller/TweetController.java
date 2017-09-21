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
	
	@GetMapping
	public List<TweetDto> getAllTweets(){
		return tweetService.getAllTweets();
	}
	@GetMapping("tweets/{id}")
	public TweetDto getTweet(@PathVariable Integer id){
		return tweetService.getTweet(id);
	}
	
	@GetMapping("tweets/{id}/likes")
	public List<ClientDto> getClientsWhoLiked(@PathVariable Integer id){
		return tweetService.getClientsWhoLiked(id);
	}
	
	@GetMapping("tweets/{id}/mentions")
	public List<ClientDto> getMentionsInTweet(@PathVariable Integer id) {
		return tweetService.getMentionsInTweet(id);
	}
	
	@PostMapping
	public TweetDto postTweet(@RequestBody TweetDto tweetDto){
		return tweetService.postTweet(tweetDto);
	}
	
	@PostMapping("tweets/{id}/like")
	public void likeTweet(@PathVariable Integer id, @RequestBody TweetDto tweetDto) {
		tweetService.likeTweet(id, tweetDto);
	}
	
	
	
	@PostMapping("tweets/{id}/repost")
	public TweetDto repostTweet(@PathVariable Integer id, @RequestBody TweetDto tweetDto) {
		return tweetService.repostTweet(id, tweetDto);
	}
	@GetMapping("tweets/{id}/reposts")
	public List<TweetDto> getReposts(@PathVariable Integer id) {
		return tweetService.getReposts(id);
	}
	
	@PostMapping("tweets/{id}/reply")
	public TweetDto replyTweet(@PathVariable Integer id, @RequestBody TweetDto tweetDto){
		return tweetService.replyTweet(id, tweetDto);
	}
	@GetMapping("tweets/{id}/replies")
	public List<TweetDto> getReplies(@PathVariable Integer id){
		return tweetService.getReplies(id);
	}
	
	@DeleteMapping("tweets/{id}")
	public TweetDto deleteTweet(@PathVariable Integer id) {
		return tweetService.deleteTweet(id);
	}
	
	
	///////////////////////////////////Tag Methods////////////////////////////////////////////////////////
	@GetMapping("tags/{label}")
	public List<HashTagDto> getTagsByLabel(@PathVariable String label){
		return tweetService.getTagByLabel(label);
	}
	@GetMapping("tags")
	public List<HashTagDto> getAllTags() {
		return tweetService.getAllTags();
	}
	@GetMapping("validate/tag/exists/{label}") 
	public Boolean hashTagExists(@PathVariable String label) {
		return tweetService.hashTagExists(label);
	}
	@GetMapping("tweets/{id}/tags")
	public List<HashTagDto> getTagFromTweet(@PathVariable Integer id){
		return tweetService.getTagFromTweet(id);
	}

}
