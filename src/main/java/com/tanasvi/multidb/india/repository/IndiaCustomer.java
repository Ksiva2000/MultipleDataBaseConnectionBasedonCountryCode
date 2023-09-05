package com.tanasvi.multidb.india.repository;

import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.repository.CustomerRepo;

@Repository
public interface IndiaCustomer extends CustomerRepo{

	   @Override
	    default String getCountry() {
	        return "India"; // Provide the appropriate country name for this repository
	    }
}
