package com.inn.dms.outstanding.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "OUSTANDINGAMOUNT")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Oustanding implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="oustanding_id_pk")
	private long id;
	
	@Column(name = "outstandingamount")
	private Long outstandingAmount;
	
	@Column(name = "customer_id_audit")
	private Long custAduitID;
	
	@Column(name = "transaction_id_audit")
	private Long transAuditID;
	
	@Column(name = "lasttransactiontype")
	private String lastTransactionType;
	
	@Column(name = "lasttransactiondate")
	private Timestamp lastTransactionDate;

	@Column(name = "lasttransactionamount")
	private Long lastTransactionAmount;

	public Long getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Long outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Long getCustAduitID() {
		return custAduitID;
	}

	public void setCustAduitID(Long custAduitID) {
		this.custAduitID = custAduitID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Long getTransAuditID() {
		return transAuditID;
	}

	public void setTransAuditID(Long transAuditID) {
		this.transAuditID = transAuditID;
	}

	
	public String getLastTransactionType() {
		return lastTransactionType;
	}

	public void setLastTransactionType(String lastTransactionType) {
		this.lastTransactionType = lastTransactionType;
	}

	public Timestamp getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(Timestamp lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	public Long getLastTransactionAmount() {
		return lastTransactionAmount;
	}

	public void setLastTransactionAmount(Long lastTransactionAmount) {
		this.lastTransactionAmount = lastTransactionAmount;
	}

	@Override
	public String toString() {
		return "Oustanding [id=" + id + ", outstandingAmount=" + outstandingAmount + ", custAduitID=" + custAduitID
				+ ", transAuditID=" + transAuditID + ", lastTransactionType=" + lastTransactionType
				+ ", lastTransactionDate=" + lastTransactionDate + ", lastTransactionAmount=" + lastTransactionAmount
				+ "]";
	}
}
