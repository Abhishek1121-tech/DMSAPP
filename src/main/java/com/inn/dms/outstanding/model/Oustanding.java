package com.inn.dms.outstanding.model;

import java.io.Serializable;

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
	
	@Column(name = "transactiontype")
	private String transactionType;

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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "Oustanding [id=" + id + ", outstandingAmount=" + outstandingAmount + ", custAduitID=" + custAduitID
				+ ", transactionType=" + transactionType + "]";
	}
}
