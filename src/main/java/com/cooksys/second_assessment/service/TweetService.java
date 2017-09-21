package com.cooksys.second_assessment.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cooksys.second_assessment.dto.ClientDto;
import com.cooksys.second_assessment.dto.HashTagDto;
import com.cooksys.second_assessment.dto.TweetDto;
import com.cooksys.second_assessment.entity.Client;
import com.cooksys.second_assessment.entity.HashTag;
import com.cooksys.second_assessment.entity.Tweet;
import com.cooksys.second_assessment.mapper.ClientMapper;
import com.cooksys.second_assessment.mapper.HashTagMapper;
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
	private HashTagMapper hashTagMapper;
	
	public TweetService(HashTagMapper hashTagMapper, TweetRepository tweetRepository, HashTagRepository hashTagRepository, ClientMapper clientMapper, TweetMapper tweetMapper, ClientRepository clientRepository, ClientService clientService) {
		this.tweetRepository = tweetRepository;
		this.clientMapper = clientMapper;
		this.tweetMapper = tweetMapper;
		this.clientRepository = clientRepository;
		this.clientService = clientService;
		this.hashTagRepository = hashTagRepository;
		this.hashTagMapper = hashTagMapper;
	}

	public List<TweetDto> getAllTweets() {
		return tweetMapper.fromTweetList(tweetRepository.findAll());
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
		List<HashTag> tagsInTweet = new ArrayList<>();
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
				if (word.startsWith("#")) {
					if(!hashTagExists(word.substring(1))) {
						HashTag hashTag = new HashTag();
						hashTag.setLabel(word.substring(1));
						tagsInTweet.add(hashTag);
						hashTagRepository.save(hashTag);
					} else{
						tagsInTweet.add(hashTagRepository.findByLabel(word.substring(1)));
					}
				}
			}
		}
		for(HashTag tag: tagsInTweet){
			tag.getTweetsWithHashTag().add(tweet);
			hashTagRepository.save(tag);
			tweet.getHashTags().add(tag);
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

	
	
	public List<HashTagDto> getTagFromTweet(Integer id) {
		return hashTagMapper.fromHashTagList(tweetRepository.findOneById(id).getHashTags());
	}
	public Boolean hashTagExists(String label) {
		if (hashTagRepository.findByLabel(label) != null)
			return true;
		else
			return false;
	}
	public List<HashTagDto> getAllTags() {
		return hashTagMapper.fromHashTagList(hashTagRepository.findAll());
	}

	public List<HashTagDto> getTagByLabel(String label) {
		return hashTagMapper.fromHashTagList(hashTagRepository.findByLabelAndIsActiveTrue(label));
	}

	@Transactional
	public TweetDto replyTweet(Integer id, TweetDto tweetDto) {
		Tweet oldTweet = tweetRepository.findOneById(id);
		if (oldTweet == null || 
		!clientService.getClientExists(tweetDto.getCredentials().getUsername()) ||
		tweetDto.getContent() == null)
			return null;
		
		else {
		postTweet(tweetDto);
		Tweet newTweet = tweetRepository.findTopByCredentialsUsernameOrderByIdDesc(tweetDto.getCredentials().getUsername());
		oldTweet.getChildTweets().add(newTweet);
		tweetRepository.save(oldTweet);
		newTweet.setParentTweet(oldTweet);
		return tweetMapper.toDto(newTweet);
	}

}


}
