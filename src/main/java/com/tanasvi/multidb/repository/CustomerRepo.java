package com.tanasvi.multidb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.entity.CustomerEntity;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer>{
	
	String getCountry();

}
