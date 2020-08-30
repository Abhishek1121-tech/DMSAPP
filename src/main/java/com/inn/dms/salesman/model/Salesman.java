package com.inn.dms.salesman.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import com.inn.dms.customer.model.Customer;


@Entity
@Table(name="SALESMAN")

public class Salesman implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="salesman_id_pk")
	private long id;
	
	@Column(name ="name")
	private String name;
	
	@Column(name="mobile")
	private long mobile;
	
	
	@Column(name="salestarget")
	private long target;
	
	@CreationTimestamp
	@Column(name = "joiningDate")
	private Timestamp joiningDate;
	
	@UpdateTimestamp
	@Column(name = "modifydate")
	private Timestamp modifyDate;
	
	@OneToMany(mappedBy = "salesman")
	private List<Customer> customer = new ArrayList<Customer>();
	
	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
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

	public long getTarget() {
		return target;
	}

	public void setTarget(long target) {
		this.target = target;
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
		return "Salesman [id=" + id + ", name=" + name + ", mobile=" + mobile + ", target=" + target + ", joiningDate="
				+ joiningDate + ", modifyDate=" + modifyDate + ", customer=" + customer + "]";
	}
}
