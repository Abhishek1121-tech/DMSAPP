package com.inn.dms.report.wrapper;

import com.inn.dms.customer.model.Customer;
import com.inn.dms.outstanding.model.Oustanding;

public class OutsatndingCustomerWrapper {
	
	private Oustanding oustanding;
	private Customer customer;
	public OutsatndingCustomerWrapper(Oustanding oustanding,Customer customer) {
		this.oustanding=oustanding;
		this.customer=customer;
	}
	public Oustanding getOustanding() {
		return oustanding;
	}
	public void setOustanding(Oustanding oustanding) {
		this.oustanding = oustanding;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
