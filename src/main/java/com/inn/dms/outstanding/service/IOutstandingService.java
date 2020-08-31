package com.inn.dms.outstanding.service	;

import java.sql.Timestamp;
import java.util.List;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.outstanding.model.Oustanding;
import com.inn.dms.report.wrapper.OutsatndingCustomerWrapper;

public interface IOutstandingService {
	
	public Oustanding persistOutstandingAmountSync(Customer customer, Billing billing);

	public List<Oustanding> findAll();
	public List<OutsatndingCustomerWrapper> getOutstandingwithCustomer();

	List<OutsatndingCustomerWrapper> getOutstandingwithCustomer(Timestamp starTimestamp, Timestamp endTimestamp);

}
