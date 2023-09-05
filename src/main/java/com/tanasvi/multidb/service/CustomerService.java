package com.tanasvi.multidb.service;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tanasvi.multidb.entity.CustomerEntity;
import com.tanasvi.multidb.model.Customer;
import com.tanasvi.multidb.repository.CustomerRepo;

@Service
@Transactional
public class CustomerService {

	private final Map<Object, CustomerRepo> repoMap;
	@Autowired
	  public CustomerService(Collection<CustomerRepo> sturepo) {
	    	repoMap = sturepo.stream()
	                             .collect(Collectors.toMap(repo -> repo.getCountry().toLowerCase(), Function.identity()));
	    }
	
	public String saveCustomerDataByCountry(String country,Customer cust) {
		
		CustomerRepo repo=repoMap.get(country.toLowerCase());
		if (repo != null) {
		CustomerEntity entity = new CustomerEntity();
		entity.setName(cust.getName());
		entity.setC_order(cust.getC_order());
		entity.setPrice(cust.getPrice());
		repo.save(entity);
		 return "SuccessfullyDone";
	} else {
        throw new IllegalArgumentException("Invalid country: " + country);
    	}
	}
	
    public List<CustomerEntity> getDetailsBasedOnCountry(String country) throws Exception {
    	CustomerRepo repo = repoMap.get(country.toLowerCase());
        if (repo != null) {
            return repo.findAll();
        } else {
            throw new Exception("Please choose the correct country: " + country);
        }
    }
}
