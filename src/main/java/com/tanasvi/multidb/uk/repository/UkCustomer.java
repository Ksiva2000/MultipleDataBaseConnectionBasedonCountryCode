package com.tanasvi.multidb.uk.repository;

import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.repository.CustomerRepo;

@Repository
public interface UkCustomer extends CustomerRepo{

	 @Override
	    default String getCountry() {
	        return "Uk"; // Provide the appropriate country name for this repository
	    }
}
