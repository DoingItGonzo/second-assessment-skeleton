package com.cooksys.second_assessment.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class HashTag {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique=true)
	private String label;
	@CreationTimestamp
	@Column(updatable=false)
	private Date firstUsed;
	// @UpdateTimestamp is refreshed anytime the owning entity is updated
	@UpdateTimestamp
	private Date lastUsed;
	@ManyToMany(mappedBy="hashTags")
	private List<Tweet> tweetsWithHashTag = new ArrayList<>();
	private Boolean isActive=true;
	
	

	public Integer getId() {
		return id;
	}
	public List<Tweet> getTweetsWithHashTag() {
		return tweetsWithHashTag;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTweetsWithHashTag(List<Tweet> tweetsWithHashTag) {
		this.tweetsWithHashTag = tweetsWithHashTag;
	}
	public Integer getid() {
		return id;
	}
	public void setid(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public Date getFirstUsed() {
		return firstUsed;
	}
	public Date getLastUsed() {
		return lastUsed;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setFirstUsed(Date firstUsed) {
		this.firstUsed = firstUsed;
	}
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
		HashTag other = (HashTag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
