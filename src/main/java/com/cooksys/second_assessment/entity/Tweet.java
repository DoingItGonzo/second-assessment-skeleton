package com.cooksys.second_assessment.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

//@Entity
public class Tweet {

	@Id
	@GeneratedValue
	private Integer id;
	private String author;
	@CreationTimestamp
	@Column(nullable=false)
	private Date posted;
	private String content;
	public HashTag hashTag;
	private Boolean isActive;
	public List<Client> clientsWhoLiked;
	
	
	
	public List<Client> getClientsWhoLiked() {
		return clientsWhoLiked;
	}

	public void setClientsWhoLiked(List<Client> clientsWhoLiked) {
		this.clientsWhoLiked = clientsWhoLiked;
	}

	public HashTag getHashTag() {
		return hashTag;
	}

	public void setHashTag(HashTag hashTag) {
		this.hashTag = hashTag;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getId() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public Date getPosted() { 
		return posted;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setPosted(Date posted) {
		this.posted = posted;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
