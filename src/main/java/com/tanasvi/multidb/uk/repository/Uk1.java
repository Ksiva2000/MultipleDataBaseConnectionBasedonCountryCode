package com.tanasvi.multidb.uk.repository;

import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.repository.StudentRepo;

@Repository
public interface Uk1 extends StudentRepo{

	   @Override
	    default String getCountry() {
	        return "Uk"; // Provide the appropriate country name for this repository
	    }
}
