package com.inn.dms.payments.model;

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
@Table(name ="PAYMENT")
public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="payment_id_pk")
	private long id;
	
	@Column(name="paymentamount")
	private long paymentAmount;
	
	@Column(name="remarks")
	private String descriptionRemarks;
	
	@CreationTimestamp
	@Column(name = "paymentdate")
	private Date paymentDate;
	
	@UpdateTimestamp
	@Column(name = "modifiedpaymentdate")
	private Date modifiedPaymentDate;

	@ManyToOne
	@JoinColumn(name="customer_id_pk", nullable=false)
	private Customer customer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPaymentAmount() {
		return paymentAmount;
	}

	public Date getModifiedPaymentDate() {
		return modifiedPaymentDate;
	}

	public void setModifiedPaymentDate(Date modifiedPaymentDate) {
		this.modifiedPaymentDate = modifiedPaymentDate;
	} 
	
	public void setPaymentAmount(long paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getDescriptionRemarks() {
		return descriptionRemarks;
	}

	public void setDescriptionRemarks(String descriptionRemarks) {
		this.descriptionRemarks = descriptionRemarks;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", paymentAmount=" + paymentAmount + ", descriptionRemarks=" + descriptionRemarks
				+ ", paymentDate=" + paymentDate + ", modifiedPaymentDate=" + modifiedPaymentDate + ", customer="
				+ customer + "]";
	}
}
