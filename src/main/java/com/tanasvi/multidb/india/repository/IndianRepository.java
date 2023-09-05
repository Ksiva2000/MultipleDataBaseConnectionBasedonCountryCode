package com.tanasvi.multidb.india.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.entity.UsersEntity;
import com.tanasvi.multidb.repository.CommonRepo;

@Repository
public interface IndianRepository extends CommonRepo {
	   @Override
	    default String getCountry() {
	        return "India"; // Provide the appropriate country name for this repository
	    }

}