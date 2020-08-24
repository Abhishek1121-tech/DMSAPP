package com.inn.dms.customer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inn.dms.customer.model.Customer;
import com.inn.dms.customer.service.ICustomerService;
import com.inn.dms.salesman.model.Salesman;
@RestController
public class CustomerRest {

	 private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRest.class);
	 
	 @Autowired
	 ICustomerService iCustomerService;
	 
	 @PostMapping(path = "/saveCustomerDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<String> saveCustomerDetail(@RequestBody Customer customer, @RequestParam Long mobile)
	{
		 String oString =iCustomerService.save(customer, mobile);
		 if (oString!=null && oString.contains("user_found"))
		 {
			 return new ResponseEntity<String>("Hi admin, customer user already found with mobile number"+customer.getMobile(), HttpStatus.OK);
		 }else if (oString.contains("salesman_not_exists"))
		 {
			 return new ResponseEntity<String>("Hi admin, Salesman you are trying to map not exists with us"+mobile, HttpStatus.OK);
		 }else
		 {	 
		 return new ResponseEntity<String>("Hi admin, user "+oString+" is created succesfully", HttpStatus.CREATED);
		}
	}
}
