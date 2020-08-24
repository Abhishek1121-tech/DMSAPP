package com.inn.dms.customer.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.inn.dms.salesman.model.Salesman;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="customer_id_pk")
	private long id;
	
	@Column(name ="name")
	private String name;
	
	@Column(name="mobile")
	private long mobile;
	
	@Column(name="area")
	private String custArea;
	
	@Column(name = "creditlimit")
	private long creditLimit;
	
	@CreationTimestamp
	@Column(name = "joiningDate")
	private Date joiningDate;
	
	@UpdateTimestamp
	@Column(name = "modifydate")
	private Date modifyDate;
	
	@ManyToOne
    @JoinColumn(name="salesman_id_pk", nullable=false)
	private Salesman salesman;

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getCustArea() {
		return custArea;
	}

	public void setCustArea(String custArea) {
		this.custArea = custArea;
	}

	public long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobile=" + mobile + ", custArea=" + custArea
				+ ", creditLimit=" + creditLimit + ", joiningDate=" + joiningDate + ", modifyDate=" + modifyDate + "]";
	}
	
	

}
