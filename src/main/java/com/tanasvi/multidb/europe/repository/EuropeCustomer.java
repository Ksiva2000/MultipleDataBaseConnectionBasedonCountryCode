package com.tanasvi.multidb.europe.repository;

import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.repository.CustomerRepo;


@Repository
public interface EuropeCustomer extends CustomerRepo{

	   @Override
	    default String getCountry() {
	        return "Europe"; // Provide the appropriate country name for this repository
	    }
}
