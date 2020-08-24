package com.inn.dms.salesman.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	private Date joiningDate;
	
	@UpdateTimestamp
	@Column(name = "modifydate")
	private Date modifyDate;
	
	@OneToMany(mappedBy = "salesman")
	private List<Customer> customer = new ArrayList<Customer>();
	
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
		return "Salesman [id=" + id + ", name=" + name + ", mobile=" + mobile + ", target=" + target + ", joiningDate="
				+ joiningDate + ", modifyDate=" + modifyDate + "]";
	}

}
