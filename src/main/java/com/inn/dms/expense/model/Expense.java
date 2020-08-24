package com.inn.dms.expense.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "EXPENSE")
public class Expense implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="expense_id_pk")
	private long id;
	
	@Column(name="expensetype")
	private String expenseType;
	
	@Column(name="expenseamount")
	private long expenseAmount;
	
	@Column(name="remarks")
	private String descriptionRemarks;
	
	@CreationTimestamp
	@Column(name = "expensedate")
	private Date expenseDate;
	
	@UpdateTimestamp
	@Column(name = "modifiedexpensedate")
	private Date modifiedExpenseDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(long expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getDescriptionRemarks() {
		return descriptionRemarks;
	}

	public void setDescriptionRemarks(String descriptionRemarks) {
		this.descriptionRemarks = descriptionRemarks;
	}



	public Date getModifiedExpenseDate() {
		return modifiedExpenseDate;
	}

	public void setModifiedExpenseDate(Date modifiedExpenseDate) {
		this.modifiedExpenseDate = modifiedExpenseDate;
	}


	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", expenseType=" + expenseType + ", expenseAmount=" + expenseAmount
				+ ", descriptionRemarks=" + descriptionRemarks + ", expenseDate=" + expenseDate
				+ ", modifiedExpenseDate=" + modifiedExpenseDate + "]";
	}
}
