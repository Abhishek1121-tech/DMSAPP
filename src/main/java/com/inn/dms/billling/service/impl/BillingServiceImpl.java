package com.inn.dms.billling.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inn.dms.billling.dao.IBillingDao;
import com.inn.dms.billling.model.Billing;
import com.inn.dms.billling.service.IBillingService;
import com.inn.dms.common.warpper.CustomerMobileWrapper;
import com.inn.dms.customer.dao.ICustomerDao;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.outstanding.model.Oustanding;
import com.inn.dms.outstanding.service.IOutstandingService;
import com.inn.dms.report.wrapper.SalesmansalesWrapper;

@Component
public class BillingServiceImpl implements IBillingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BillingServiceImpl.class);
	 
	@Autowired
	private IBillingDao iBillingDao;
	
	@Autowired
	private ICustomerDao iCustomerDao;
	
	@Autowired
	private IOutstandingService iOutstandingService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public String save(Billing billing,Long mobile) {
		Customer customer = iCustomerDao.getCustomerIdByMobile(mobile);
		
		if (customer == null)
		{
			return "customer_not_exists";
		}else {
			Billing billing_save = new Billing();
			billing_save.setBillAmount(billing.getBillAmount());
			billing_save.setDescriptionRemarks(billing.getDescriptionRemarks());
			billing_save.setCustomer(customer);
			billing_save.setTransactionType(billing.getTransactionType());
			billing=iBillingDao.save(billing_save);
			Oustanding oustanding=iOutstandingService.persistOutstandingAmountSync(customer,billing);
			return String.valueOf(billing.getId()+" outstanding amount left "+oustanding.getOutstandingAmount());
		}
	}

	@Override
	public List<CustomerMobileWrapper> findAllCustomerByMobile() {
		return iCustomerDao.getCustomerIdByMobile();
	}

	@Override
	public List<CustomerMobileWrapper> searchCustomerByMobile(Long mobile) {
		LOGGER.info("Mobile Number search text {}",mobile);
		return iCustomerDao.searchCustomerIdByMobile(mobile);
	}

	@Override
	public List<SalesmansalesWrapper> findAllByCustomerNSalesman() {
		return iBillingDao.findAllByCustomerNSalesman();
	}

}
