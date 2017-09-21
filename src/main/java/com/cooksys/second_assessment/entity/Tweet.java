package com.cooksys.second_assessment.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Tweet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Credentials credentials;
	@ManyToOne
	private Client author;
	@CreationTimestamp
	@Column(updatable=false)
	private Date posted;
	private String content;
	@ManyToMany(mappedBy="mentionedBy")
	private List<Client> mentions;
	@ManyToMany
	private List<HashTag> hashTags;
	private Boolean isActive = true;
	@ManyToMany(mappedBy="likedTweets")
	private List<Client> clientsWhoLiked;
	
	
	
	public List<HashTag> getHashTags() {
		return hashTags;
	}

	public void setHashTags(List<HashTag> hashTags) {
		this.hashTags = hashTags;
	}

	public Client getAuthor() {
		return author;
	}

	public void setAuthor(Client author) {
		this.author = author;
	}

	public List<Client> getClientsWhoLiked() {
		return clientsWhoLiked;
	}

	public void setClientsWhoLiked(List<Client> clientsWhoLiked) {
		this.clientsWhoLiked = clientsWhoLiked;
	}

//	public HashTag getHashTag() {
//		return hashTag;
//	}
//
//	public void setHashTag(HashTag hashTag) {
//		this.hashTag = hashTag;
//	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getId() {
		return id;
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
	
	public void setPosted(Date posted) {
		this.posted = posted;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public List<Client> getMentions() {
		return mentions;
	}

	public void setMentions(List<Client> mentions) {
		this.mentions = mentions;
	}
	
	
	
}
