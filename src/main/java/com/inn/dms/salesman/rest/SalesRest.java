package com.inn.dms.salesman.rest;

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

import com.inn.dms.salesman.model.Salesman;
import com.inn.dms.salesman.service.ISalesmanService;
import com.inn.dms.salesman.wrapper.SalesManMobileWrapper;

@RestController
public class SalesRest {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(SalesRest.class);
	 
	@Autowired
	ISalesmanService iSalesmanService;
	
	@PostMapping(path = "/saveSalesman", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveSalesman(@RequestBody Salesman salesman)
	{
		String ouString = iSalesmanService.save(salesman);
		LOGGER.info("save message {}",ouString);
		if (ouString.equals("exists"))
		{
			return new ResponseEntity<String>("Hi admin, User Already Exist with mobile number"+salesman.getMobile(), HttpStatus.OK);
		}else
		{
		return new ResponseEntity<String>("Hi admin, user "+ouString+" is created succesfully", HttpStatus.CREATED);
	}
	}
	
	@GetMapping(path = "/findAllSalesmanByMobile" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SalesManMobileWrapper> findAllSalesmanByMobile()
	{
		return iSalesmanService.findAllSalesmanByMobile();
		
	}
	@GetMapping(path = "/searchSalesmanByMobile" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Long> searchSalesmanByMobile(@RequestParam Long mobile)
	{
		LOGGER.info("mobile {}",mobile);
		return iSalesmanService.searchSalesmanByMobile(mobile);
		
	}
	}

