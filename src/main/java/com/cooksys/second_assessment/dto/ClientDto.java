package com.cooksys.second_assessment.dto;

import com.cooksys.second_assessment.entity.Credentials;
import com.cooksys.second_assessment.entity.Profile;

public class ClientDto {
	
	private Credentials credentials;
	private Profile profile;
//	private Timestamp timestamp;
//	private Long timestamp;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}


}

