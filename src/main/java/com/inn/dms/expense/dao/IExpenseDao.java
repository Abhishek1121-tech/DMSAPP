package com.inn.dms.expense.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.dms.expense.model.Expense;

@Repository
public interface IExpenseDao extends JpaRepository<Expense, Long>{

}
