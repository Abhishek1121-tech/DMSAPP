package com.inn.dms.expense.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inn.dms.expense.model.Expense;
import com.inn.dms.expense.service.IExpenseService;

@RestController
public class ExpenseRest {


	 private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRest.class);
	 
	 @Autowired
	 IExpenseService iExpenseService; 
	
	 @PostMapping(path = "/saveExpense", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<String> saveExpense(@RequestBody Expense expense)
	{
		 String oString =iExpenseService.save(expense);
		
		 return new ResponseEntity<String>("Hi admin, Expense id  "+oString+" is created succesfully", HttpStatus.CREATED);
		
	}
	 
	
}
