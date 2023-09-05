package com.tanasvi.multidb.europe.repository;

import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.repository.StudentRepo;

@Repository
public interface Eu1 extends StudentRepo{

	   @Override
	    default String getCountry() {
	        return "Europe"; // Provide the appropriate country name for this repository
	    }
}
