package com.cooksys.second_assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.second_assessment.entity.HashTag;

public interface HashTagRepository extends JpaRepository<HashTag, Integer>{
	
	HashTag findByLabel(String label);

}
