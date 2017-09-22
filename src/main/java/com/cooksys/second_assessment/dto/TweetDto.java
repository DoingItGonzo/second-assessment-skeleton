package com.cooksys.second_assessment.dto;

import java.util.Date;

import com.cooksys.second_assessment.entity.Credentials;

public class TweetDto {

	private Credentials credentials;
	private String content;
//	private Integer id;
//	private Long timestamp;
	
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
