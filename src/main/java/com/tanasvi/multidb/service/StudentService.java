package com.tanasvi.multidb.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanasvi.multidb.entity.CustomerEntity;
import com.tanasvi.multidb.entity.StudentEntity;
import com.tanasvi.multidb.model.CombiningThreeTables;
import com.tanasvi.multidb.model.Student;
import com.tanasvi.multidb.repository.CustomerRepo;
import com.tanasvi.multidb.repository.StudentRepo;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Map<Object, StudentRepo> repoMap;
	@Autowired
	  public StudentService(Collection<StudentRepo> sturepo) {
	    	repoMap = sturepo.stream()
	                             .collect(Collectors.toMap(repo -> repo.getCountry().toLowerCase(), Function.identity()));
	    }
	  
	  public String saveRegisterDataBasedOnCountry(String country, Student u) {
		  StudentRepo repo = repoMap.get(country.toLowerCase());
	        if (repo != null) {
	          StudentEntity entity = new StudentEntity();
	          entity.setName(u.getName());
	          entity.setMarks(u.getMarks());
	          repo.save(entity);
	            return "SuccessfullyDone";
	        } else {
	            throw new IllegalArgumentException("Invalid country: " + country);
	        }
	    }
	  
	  public List<StudentEntity> getDetailsBasedOnCountry(String country) throws Exception {
		  StudentRepo repo = repoMap.get(country.toLowerCase());
	        if (repo != null) {
	            return repo.findAll();
	        } else {
	            throw new Exception("Please choose the correct country: " + country);
	        }
	    }
}
