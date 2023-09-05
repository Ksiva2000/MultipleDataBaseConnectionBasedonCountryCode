package com.tanasvi.multidb.usa.repository;

import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.repository.StudentRepo;
@Repository
public interface Usa1 extends StudentRepo {
	
	   @Override
	    default String getCountry() {
	        return "Usa"; // Provide the appropriate country name for this repository
	    }

}
