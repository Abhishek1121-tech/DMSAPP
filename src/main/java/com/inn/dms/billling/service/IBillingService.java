package com.inn.dms.billling.service;

import java.util.List;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.common.warpper.CustomerMobileWrapper;
import com.inn.dms.report.wrapper.SalesmansalesWrapper;

public interface IBillingService {
	
	public String save(Billing billing,Long mobile);
	public List<CustomerMobileWrapper> findAllCustomerByMobile();
	public List<CustomerMobileWrapper> searchCustomerByMobile(Long mobile);
	public List<SalesmansalesWrapper> findAllByCustomerNSalesman();

}
