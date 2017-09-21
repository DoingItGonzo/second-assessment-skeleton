package com.cooksys.second_assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.second_assessment.entity.HashTag;

public interface HashTagRepository extends JpaRepository<HashTag, Integer>{
	
	HashTag findByLabel(String label);
	
	List<HashTag> findByLabelAndIsActiveTrue(String label);
	
	HashTag findByIdAndIsActiveTrue(Integer id);
	
	HashTag findByLabelAndIsActiveFalse(String label);

}
