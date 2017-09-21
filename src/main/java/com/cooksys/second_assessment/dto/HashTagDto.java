package com.cooksys.second_assessment.dto;

import java.util.Date;

public class HashTagDto {
	
	private String label;
	private Date firstUsed;
	private Date lastUsed;
	
	
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

}
