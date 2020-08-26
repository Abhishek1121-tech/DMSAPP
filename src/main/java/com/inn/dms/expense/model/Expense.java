package com.inn.dms.expense.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "EXPENSE")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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
	private Timestamp expenseDate;
	
	@UpdateTimestamp
	@Column(name = "modifiedexpensedate")
	private Timestamp modifiedExpenseDate;

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

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public Timestamp getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Timestamp expenseDate) {
		this.expenseDate = expenseDate;
	}

	public Timestamp getModifiedExpenseDate() {
		return modifiedExpenseDate;
	}

	public void setModifiedExpenseDate(Timestamp modifiedExpenseDate) {
		this.modifiedExpenseDate = modifiedExpenseDate;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", expenseType=" + expenseType + ", expenseAmount=" + expenseAmount
				+ ", descriptionRemarks=" + descriptionRemarks + ", expenseDate=" + expenseDate
				+ ", modifiedExpenseDate=" + modifiedExpenseDate + "]";
	}
}
