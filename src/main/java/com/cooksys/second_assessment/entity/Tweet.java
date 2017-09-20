package com.cooksys.second_assessment.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Tweet {

	@Id
	@GeneratedValue
	private Integer id;
	private String author;
	private Date posted;
	private String content;
	
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
