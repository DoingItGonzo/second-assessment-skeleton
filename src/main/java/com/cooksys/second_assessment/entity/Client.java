package com.cooksys.second_assessment.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@CreationTimestamp
	@Column(updatable=false)
	private Date dateOfAccountCreation;
	private Boolean isActive = true;
	
	private Credentials credentials;
	private Profile profile;
	@OneToMany(mappedBy="author")
	private List<Tweet> allTweets;
	@ManyToMany
	private List<Client> followedFeeds;
	@ManyToMany(mappedBy="followedFeeds")
	private List<Client> followers;
	@ManyToMany
	private List<Tweet> likedTweets;
	

	public List<Tweet> getAllTweets() {
		return allTweets;
	}
	public void setAllTweets(List<Tweet> allTweets) {
		this.allTweets = allTweets;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Date getDateOfAccountCreation() {
		return dateOfAccountCreation;
	}
	public void setDateOfAccountCreation(Date dateOfAccountCreation) {
		this.dateOfAccountCreation = dateOfAccountCreation;
	}
	public List<Client> getFollowedFeeds() {
		return followedFeeds;
	}
	public List<Client> getFollowers() {
		return followers;
	}
	public void setFollowedFeeds(List<Client> followedFeeds) {
		this.followedFeeds = followedFeeds;
	}
	public void setFollowers(List<Client> followers) {
		this.followers = followers;
	}
	public List<Tweet> getLikedTweets() {
		return likedTweets;
	}
	public void setLikedTweets(List<Tweet> likedTweets) {
		this.likedTweets = likedTweets;
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
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
