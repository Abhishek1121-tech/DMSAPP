package com.inn.dms.payments.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inn.dms.customer.dao.ICustomerDao;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.outstanding.model.Oustanding;
import com.inn.dms.outstanding.service.IOutstandingService;
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
	
	@Autowired
	private IOutstandingService iOutstandingService;
	
	
	@Override
	public String save(Payment payment, Long mobile) {
		Customer customer = iCustomerDao.getCustomerIdByMobile(mobile);
		if (customer==null) {
			return "customer_not_exists";
		}else {
		Payment billing_save = new Payment();
		billing_save.setPaymentAmount(payment.getPaymentAmount());
		billing_save.setDescriptionRemarks(payment.getDescriptionRemarks());
		billing_save.setCustomer(customer);
		payment=iPaymentDao.save(billing_save);
		Oustanding oustanding=iOutstandingService.persistOutstandingAmountSync(customer.getId(),payment.getPaymentAmount(),"payment");
		return String.valueOf(payment.getId()+" outstanding amount left "+oustanding.getOutstandingAmount());	
		}
		}

}
