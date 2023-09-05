package com.tanasvi.multidb.europe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.entity.UsersEntity;
import com.tanasvi.multidb.repository.CommonRepo;

@Repository
public interface EuropeRepository extends CommonRepo{
	   @Override
	    default String getCountry() {
	        return "Europe"; // Provide the appropriate country name for this repository
	    }
}
