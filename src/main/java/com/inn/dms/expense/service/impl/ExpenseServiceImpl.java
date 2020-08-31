package com.inn.dms.expense.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inn.dms.expense.dao.IExpenseDao;
import com.inn.dms.expense.model.Expense;
import com.inn.dms.expense.service.IExpenseService;
@Component
public class ExpenseServiceImpl implements IExpenseService {

	@Autowired
	private IExpenseDao iExpenseDao;
	
	@Override
	public String save(Expense expense) {
		Expense expense_save = new Expense();
		expense_save.setExpenseType(expense.getExpenseType());
		expense_save.setExpenseAmount(expense.getExpenseAmount());
		expense_save.setDescriptionRemarks(expense.getDescriptionRemarks());
		expense=iExpenseDao.save(expense_save);
		return String.valueOf(expense.getId());
	}

	@Override
	public List<Expense> findAll() {
		// TODO Auto-generated method stub
		return iExpenseDao.findAll();
	}
	
	@Override
	public List<Expense> findAllByRange(Timestamp starTimestamp,Timestamp endTimestamp) {
		// TODO Auto-generated method stub
		return iExpenseDao.findAllByRange(new Date(starTimestamp.getTime()) ,new Date(endTimestamp.getTime()));
	}
}
