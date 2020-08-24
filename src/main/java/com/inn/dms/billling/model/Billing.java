package com.inn.dms.billling.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.inn.dms.customer.model.Customer;

@Entity
@Table(name ="BILLING")
public class Billing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="billing_id_pk")
	private long id;
	
	
	@Column(name="billamount")
	private long billAmount;
	
	@Column(name="remarks")
	private String descriptionRemarks;
	
	@CreationTimestamp
	@Column(name = "billingdate")
	private Date billingDate;
	
	@UpdateTimestamp
	@Column(name = "modifiedbillingdate")
	private Date modifiedBillingDate;
	
	@ManyToOne
	@JoinColumn(name="customer_id_pk", nullable=false)
	private Customer customer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(long billAmount) {
		this.billAmount = billAmount;
	}

	public String getDescriptionRemarks() {
		return descriptionRemarks;
	}

	public void setDescriptionRemarks(String descriptionRemarks) {
		this.descriptionRemarks = descriptionRemarks;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public Date getModifiedBillingDate() {
		return modifiedBillingDate;
	}

	public void setModifiedBillingDate(Date modifiedBillingDate) {
		this.modifiedBillingDate = modifiedBillingDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Billing [id=" + id + ", billAmount=" + billAmount + ", descriptionRemarks=" + descriptionRemarks
				+ ", billingDate=" + billingDate + ", modifiedBillingDate=" + modifiedBillingDate + ", customer="
				+ customer + "]";
	}
}
