package com.tanasvi.multidb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tanasvi.multidb.entity.StudentEntity;
import com.tanasvi.multidb.model.Student;
import com.tanasvi.multidb.service.StudentService;

@RestController
@RequestMapping(path = "api")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@PostMapping(path = "/saveStudent")
	  public ResponseEntity<String> resgisterInDb(@RequestParam String country, @RequestBody Student student) {
      String resultMessage = service.saveRegisterDataBasedOnCountry(country, student);
      if(resultMessage.equals("SuccessfullyDone")) {
      return new ResponseEntity<>(resultMessage, HttpStatus.OK);
  }
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	
	}
	
	@GetMapping(path = "/get")
	public ResponseEntity<List<StudentEntity>> getIndiaDbDetails(@RequestParam String country) throws Exception{
		List<StudentEntity> student= service.getDetailsBasedOnCountry(country);
		if(student!=null) {
			return new ResponseEntity<List<StudentEntity>>(student,HttpStatus.OK);
		}else
		return new ResponseEntity<List<StudentEntity>>(HttpStatus.BAD_REQUEST);
		
	}
}
