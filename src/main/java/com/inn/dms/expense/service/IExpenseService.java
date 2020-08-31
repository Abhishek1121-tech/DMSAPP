package com.inn.dms.expense.service;

import java.sql.Timestamp;
import java.util.List;

import com.inn.dms.expense.model.Expense;

public interface IExpenseService {

	public String save(Expense expense);

	public List<Expense> findAll();

	List<Expense> findAllByRange(Timestamp starTimestamp, Timestamp endTimestamp);
}
