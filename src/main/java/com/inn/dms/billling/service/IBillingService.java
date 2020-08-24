package com.inn.dms.billling.service;

import java.util.List;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.billling.warpper.CustomerMobileWrapper;

public interface IBillingService {
	
	public String save(Billing billing,Long mobile);
	public List<CustomerMobileWrapper> findAllCustomerByMobile();
	public List<CustomerMobileWrapper> searchCustomerByMobile(Long mobile);

}
