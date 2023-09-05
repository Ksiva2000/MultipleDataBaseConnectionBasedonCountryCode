package com.tanasvi.multidb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanasvi.multidb.entity.StudentEntity;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {

	String getCountry();

}
