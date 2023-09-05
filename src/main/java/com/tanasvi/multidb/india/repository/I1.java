package com.tanasvi.multidb.india.repository;

import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.repository.StudentRepo;

@Repository
public interface I1 extends StudentRepo{
	   @Override
	    default String getCountry() {
	        return "India"; // Provide the appropriate country name for this repository
	    }
}
