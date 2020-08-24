package com.inn.dms.billling.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inn.dms.billling.dao.IBillingDao;
import com.inn.dms.billling.model.Billing;
import com.inn.dms.billling.service.IBillingService;
import com.inn.dms.billling.warpper.CustomerMobileWrapper;
import com.inn.dms.customer.dao.ICustomerDao;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.salesman.model.Salesman;

@Component
public class BillingServiceImpl implements IBillingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BillingServiceImpl.class);
	 
	@Autowired
	private IBillingDao iBillingDao;
	
	@Autowired
	private ICustomerDao iCustomerDao;
	
	@Override
	public String save(Billing billing,Long mobile) {
		Customer customer = iCustomerDao.getCustomerIdByMobile(mobile);
		if (customer != null)
		{
			Billing billing_save = new Billing();
			billing_save.setBillAmount(billing.getBillAmount());
			billing_save.setDescriptionRemarks(billing.getDescriptionRemarks());
			billing_save.setCustomer(customer);
			billing=iBillingDao.save(billing_save);
			return String.valueOf(billing.getId());
		}
		else
		{
			return "customer_not_exists";
		}
		
	}

	@Override
	public List<CustomerMobileWrapper> findAllCustomerByMobile() {
		// TODO Auto-generated method stub
		return iCustomerDao.getCustomerIdByMobile();
	}

	@Override
	public List<CustomerMobileWrapper> searchCustomerByMobile(Long mobile) {
		// TODO Auto-generated method stub
		LOGGER.info("Mobile Number search text {}",mobile);
		return iCustomerDao.searchCustomerIdByMobile(mobile);
	}

}
