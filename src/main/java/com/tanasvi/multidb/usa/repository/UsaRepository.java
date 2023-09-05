package com.tanasvi.multidb.usa.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.entity.UsersEntity;
import com.tanasvi.multidb.repository.CommonRepo;

@Repository
public interface UsaRepository extends CommonRepo {
	
	   @Override
	    default String getCountry() {
	        return "Usa"; // Provide the appropriate country name for this repository
	    }
}
