package com.inn.dms.payments.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inn.dms.customer.dao.ICustomerDao;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.payments.dao.IPaymentDao;
import com.inn.dms.payments.model.Payment;
import com.inn.dms.payments.service.IPaymentService;
@Component
public class PaymentServiceImpl implements IPaymentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
	 
	@Autowired
	private IPaymentDao iPaymentDao;
	
	@Autowired
	private ICustomerDao iCustomerDao;
	
	@Override
	public String save(Payment payment, Long mobile) {
		Customer customer = iCustomerDao.getCustomerIdByMobile(mobile);
		if (customer != null)
		{
			Payment billing_save = new Payment();
			billing_save.setPaymentAmount(payment.getPaymentAmount());
			billing_save.setDescriptionRemarks(payment.getDescriptionRemarks());
			billing_save.setCustomer(customer);
			payment=iPaymentDao.save(billing_save);
			return String.valueOf(payment.getId());
		}
		else
		{
			return "customer_not_exists";
		}	
		}

}
