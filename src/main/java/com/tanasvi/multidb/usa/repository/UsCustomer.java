package com.tanasvi.multidb.usa.repository;

import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.repository.CustomerRepo;

@Repository
public interface UsCustomer extends CustomerRepo{

	 @Override
	    default String getCountry() {
	        return "Usa"; // Provide the appropriate country name for this repository
	    }
}
