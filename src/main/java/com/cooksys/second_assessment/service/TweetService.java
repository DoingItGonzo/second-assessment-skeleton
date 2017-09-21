package com.cooksys.second_assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.dto.HashTagDto;
import com.cooksys.second_assessment.dto.TweetDto;
import com.cooksys.second_assessment.entity.Client;
import com.cooksys.second_assessment.entity.HashTag;
import com.cooksys.second_assessment.entity.Tweet;
import com.cooksys.second_assessment.mapper.ClientMapper;
import com.cooksys.second_assessment.mapper.TweetMapper;
import com.cooksys.second_assessment.repository.ClientRepository;
import com.cooksys.second_assessment.repository.HashTagRepository;
import com.cooksys.second_assessment.repository.TweetRepository;

@Service
public class TweetService {
	
	private ClientRepository clientRepository;
	private ClientMapper clientMapper;
	private TweetRepository tweetRepository;
	private TweetMapper tweetMapper;
	private ClientService clientService;
	private HashTagRepository hashTagRepository;
	
	public TweetService(TweetRepository tweetRepository, HashTagRepository hashTagRepository, ClientMapper clientMapper, TweetMapper tweetMapper, ClientRepository clientRepository, ClientService clientService) {
		this.tweetRepository = tweetRepository;
		this.clientMapper = clientMapper;
		this.tweetMapper = tweetMapper;
		this.clientRepository = clientRepository;
		this.clientService = clientService;
		this.hashTagRepository = hashTagRepository;
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
		Client client = clientRepository.findByCredentialsUsername(tweetDto.getCredentials().getUsername());
		if (client == null || !client.getIsActive()) {
			return null;
		}
		Tweet tweet = new Tweet();
		List<Client> mentionedList = new ArrayList<>();
		tweetRepository.save(tweet);
		if(tweetDto.getContent() != null) {
			String[] splitContent = tweetDto.getContent().split(" ");
			for(String word: splitContent) {
				if (word.startsWith("@")) {
					Client mentionedClient = clientRepository.findByCredentialsUsername(word.substring(1));
					mentionedClient.getMentionedBy().add(tweet);
					clientRepository.save(mentionedClient);
					mentionedList.add(mentionedClient);
				}
/////////////////////////////////////////////////////				
				//Won't do shit while the object is still transient. 
				//It's adding the HashTag to the DataBase but not establishing the relation.
				// Probably need to instantiate it outside the if block. Might be worth splitting this . . . 
				// . . . shit into multiple methods
				
//				if (word.startsWith("#")) {
//					HashTag hashTag = new HashTag();
//					hashTag.setLabel(word.substring(1));
//					hashTagRepository.save(hashTag);
				
				//It breaks right here!!!!
//					hashTagRepository.findByLabel(word.substring(1)).getTweetsWithHashTag().add(tweet);
////					hashTag.getTweetsWithHashTag().add(tweet);
				
//					hashTagRepository.save(hashTagRepository.findByLabel(word.substring(1)));
//					tweet.getHashTags().add(hashTag);
//				}
			}
		} 
		tweet.setContent(tweetDto.getContent()); tweet.setCredentials(tweetDto.getCredentials());
		tweet.setAuthor(client);
		tweet.setMentions(mentionedList);
		client.getAllTweets().add(tweet);
		clientRepository.save(client);
		return tweetMapper.toDto(tweetRepository.save(tweet));
	}

	public void likeTweet(Integer id, TweetDto tweetDto) {
		Tweet tweet = tweetRepository.findOneById(id);
		Client client = clientRepository.findByCredentialsUsername(tweetDto.getCredentials().getUsername());
		tweet.getClientsWhoLiked().add(client);
		client.getLikedTweets().add(tweet);
		tweetRepository.save(tweet);
		clientRepository.save(client);
	}

	public List<ClientDto> getMentionsInTweet(Integer id) {
		return clientMapper.fromClientList(tweetRepository.findOneById(id).getMentions());
	}

	public Boolean hashTagExists(String label) {
		if (hashTagRepository.findByLabel(label) != null)
			return true;
		else
			return false;
	}

}
