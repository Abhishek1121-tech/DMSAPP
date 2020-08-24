package com.inn.dms.billling.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.billling.service.IBillingService;
import com.inn.dms.billling.warpper.CustomerMobileWrapper;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.customer.service.ICustomerService;
import com.inn.dms.salesman.wrapper.SalesManMobileWrapper;

@RestController
public class BillingRest {

	 private static final Logger LOGGER = LoggerFactory.getLogger(BillingRest.class);
	 
	 @Autowired
	 IBillingService iBillingService; 
	
	 @PostMapping(path = "/saveBill", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<String> saveCustomerDetail(@RequestBody Billing billing, @RequestParam Long mobile)
	{
		 String oString =iBillingService.save(billing, mobile);
		if (oString.contains("customer_not_exists"))
		 {
			 return new ResponseEntity<String>("Hi admin, Customer you are trying to map not exists with us"+mobile, HttpStatus.OK);
		 }else
		 {	 
		 return new ResponseEntity<String>("Hi admin, Bill id  "+oString+" is created succesfully", HttpStatus.CREATED);
		}
	}
	 @GetMapping(path = "/findAllCustomerByMobile" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<CustomerMobileWrapper> findAllCustomerByMobile()
		{
			return iBillingService.findAllCustomerByMobile();
			
		}
		@GetMapping(path = "/searchCustomerByMobile" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<CustomerMobileWrapper> searchCustomerByMobile(@RequestParam Long mobile)
		{
			LOGGER.info("mobile {}",mobile);
			return iBillingService.searchCustomerByMobile(mobile);
			
		}
	 
	 
}
