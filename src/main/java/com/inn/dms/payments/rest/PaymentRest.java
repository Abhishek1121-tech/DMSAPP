package com.inn.dms.payments.rest;

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

import com.inn.dms.billling.rest.BillingRest;
import com.inn.dms.billling.service.IBillingService;
import com.inn.dms.payments.model.Payment;
import com.inn.dms.payments.service.IPaymentService;

@RestController
public class PaymentRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BillingRest.class);
	 
	 @Autowired
	 IBillingService iBillingService; 
	 
	 @Autowired
	 IPaymentService iPaymentService;
	
	 @PostMapping(path = "/savePayment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<String> saveCustomerDetail(@RequestBody Payment payment, @RequestParam Long mobile)
	{
		 String oString =iPaymentService.save(payment, mobile);
		if (oString.contains("customer_not_exists"))
		 {
			 return new ResponseEntity<String>("Hi admin, Customer you are trying to map not exists with us"+mobile, HttpStatus.OK);
		 }else
		 {	 
		 return new ResponseEntity<String>("Hi admin, Payment id  "+oString+" is created succesfully", HttpStatus.CREATED);
		}
	}
}
