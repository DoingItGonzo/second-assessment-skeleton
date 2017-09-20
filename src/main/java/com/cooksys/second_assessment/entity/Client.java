package com.cooksys.second_assessment.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Client {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Credentials credentials;
	@CreationTimestamp
	@Column(updatable=false)
	private Date dateOfAccountCreation;
	private Profile profile;
	private Boolean isActive = true;
	@ManyToMany
	private List<Client> followedFeeds;
	@ManyToMany(mappedBy="followedFeeds")
	private List<Client> followers;
	

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
