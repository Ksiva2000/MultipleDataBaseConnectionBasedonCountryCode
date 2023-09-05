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

import com.tanasvi.multidb.entity.CustomerEntity;
import com.tanasvi.multidb.entity.UsersEntity;
import com.tanasvi.multidb.model.Customer;
import com.tanasvi.multidb.model.Student;
import com.tanasvi.multidb.service.CustomerService;

@RestController
@RequestMapping("api")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping(path = "/saveCustomer")
	  public ResponseEntity<String> resgisterInDb(@RequestParam String country, @RequestBody Customer customer) {
    String resultMessage = service.saveCustomerDataByCountry(country, customer);
    if(resultMessage.equals("SuccessfullyDone")) {
    return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    	}
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	
	}
	@GetMapping(path = "/getCustomers")
	public ResponseEntity<List<CustomerEntity>> getIndiaDbDetails(@RequestParam String country) throws Exception{
		List<CustomerEntity> customer= service.getDetailsBasedOnCountry(country);
		if(customer!=null) {
			return new ResponseEntity<List<CustomerEntity>>(customer,HttpStatus.OK);
		}else
		return new ResponseEntity<List<CustomerEntity>>(HttpStatus.BAD_REQUEST);
		
	}
	

}
