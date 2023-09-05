package com.tanasvi.multidb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanasvi.multidb.entity.UsersEntity;
import com.tanasvi.multidb.model.Users;
import com.tanasvi.multidb.service.MultiDbService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@Slf4j
public class TestController {

	@Autowired
	private MultiDbService dbService;
	
	
	@PostMapping(path = "/RegisterUser")
	  public ResponseEntity<String> resgisterInDb(@RequestParam String country, @RequestBody Users usersEntity) {
        String resultMessage = dbService.saveRegisterDataBasedOnCountry(country, usersEntity);
        if(resultMessage.equals("SuccessfullyDone")) {
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	
	}
	
	@GetMapping(path = "/getUsers")
	public ResponseEntity<List<UsersEntity>> getIndiaDbDetails(@RequestParam String country) throws Exception{
		List<UsersEntity> user= dbService.getDetailsBasedOnCountry(country);
		if(user!=null) {
			return new ResponseEntity<List<UsersEntity>>(user,HttpStatus.OK);
		}else
		return new ResponseEntity<List<UsersEntity>>(HttpStatus.BAD_REQUEST);
		
	}
}
