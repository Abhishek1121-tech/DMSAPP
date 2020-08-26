package com.inn.dms.customer.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.inn.dms.billling.model.Billing;
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
	private Timestamp joiningDate;
	
	@UpdateTimestamp
	@Column(name = "modifydate")
	private Timestamp modifyDate;
	@ManyToOne
    @JoinColumn(name="salesman_id_pk", nullable=false)
	private Salesman salesman;
	
	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
	private List<Billing> billing = new ArrayList<Billing>();

	public List<Billing> getBilling() {
		return billing;
	}

	public void setBilling(List<Billing> billing) {
		this.billing = billing;
	}

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

	public Timestamp getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Timestamp joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobile=" + mobile + ", custArea=" + custArea
				+ ", creditLimit=" + creditLimit + ", joiningDate=" + joiningDate + ", modifyDate=" + modifyDate
				+ ", salesman=" + salesman + ", billing=" + billing + "]";
	}
}
