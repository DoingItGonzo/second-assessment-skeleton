package com.cooksys.second_assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.second_assessment.entity.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Integer>{
	
	Tweet findOneById(Integer id);

}
