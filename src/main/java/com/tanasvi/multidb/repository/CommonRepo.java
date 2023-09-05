package com.tanasvi.multidb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanasvi.multidb.entity.UsersEntity;

public interface CommonRepo  extends JpaRepository<UsersEntity, Long>{

	String getCountry();

}
