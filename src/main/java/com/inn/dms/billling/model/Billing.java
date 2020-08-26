package com.inn.dms.billling.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.inn.dms.customer.model.Customer;


@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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
	private Timestamp billingDate;
	
	@UpdateTimestamp
	@Column(name = "modifiedbillingdate")
	private Timestamp modifiedBillingDate;
	
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

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Timestamp getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Timestamp billingDate) {
		this.billingDate = billingDate;
	}

	public Timestamp getModifiedBillingDate() {
		return modifiedBillingDate;
	}

	public void setModifiedBillingDate(Timestamp modifiedBillingDate) {
		this.modifiedBillingDate = modifiedBillingDate;
	}

	@Override
	public String toString() {
		return "Billing [id=" + id + ", billAmount=" + billAmount + ", descriptionRemarks=" + descriptionRemarks
				+ ", billingDate=" + billingDate + ", modifiedBillingDate=" + modifiedBillingDate + ", customer="
				+ customer + "]";
	}

	
	
}
