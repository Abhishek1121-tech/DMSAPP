package com.inn.dms.outstanding.service.impl;



import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.outstanding.dao.IOutstandingDao;
import com.inn.dms.outstanding.model.Oustanding;
import com.inn.dms.outstanding.service.IOutstandingService;
import com.inn.dms.report.wrapper.OutsatndingCustomerWrapper;

@Component
public class OutstandingServiceImpl implements IOutstandingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OutstandingServiceImpl.class);
	
	@Autowired
	private IOutstandingDao iOutstandingDao;

	@Override
	public synchronized Oustanding persistOutstandingAmountSync(Customer customer, Billing billing) {
		LOGGER.info("hey hurray we are in synchronized block, id {} amount {} type {} Timestamp transactionDate {}",customer.getId(),billing.getBillAmount(),billing.getTransactionType(),billing.getTransactionDate());
		Integer checkFlagForExistnace = iOutstandingDao.checkCustomerEntryExists(customer.getId());
		Oustanding oustanding = new Oustanding();
		if (checkFlagForExistnace > 0)
		{
			oustanding=iOutstandingDao.getOutstandingAmountCustID(customer.getId());
			if (billing.getTransactionType().equals("payment"))
			{
				oustanding.setOutstandingAmount(oustanding.getOutstandingAmount()-billing.getBillAmount());
			}else if (billing.getTransactionType().equals("billing")) {
				oustanding.setOutstandingAmount(oustanding.getOutstandingAmount()+billing.getBillAmount());
			}else
			{
				LOGGER.info("No known or Null type specified {}",billing.getTransactionType());
			}
		}else {
			oustanding.setCustAduitID(customer.getId());
			if (billing.getTransactionType().equals("payment"))
			{
				oustanding.setOutstandingAmount(0-billing.getBillAmount());
			}else if (billing.getTransactionType().equals("billing")) {
				oustanding.setOutstandingAmount(0+billing.getBillAmount());
			}else
			{
				LOGGER.info("No known or Null type specified {}",billing.getTransactionType());
			}		
		}
		oustanding.setLastTransactionType(billing.getTransactionType());
		oustanding.setLastTransactionDate(billing.getTransactionDate());
		oustanding.setLastTransactionAmount(billing.getBillAmount());
		oustanding.setTransAuditID(billing.getId());
		oustanding=iOutstandingDao.save(oustanding);
		return oustanding;
		}

	@Override
	public List<Oustanding> findAll() {
		return iOutstandingDao.findAll();
	}

	@Override
	public List<OutsatndingCustomerWrapper> getOutstandingwithCustomer() {
		return iOutstandingDao.getOutstandingwithCustomer();
	}
	
	@Override
	public List<OutsatndingCustomerWrapper> getOutstandingwithCustomer(Timestamp starTimestamp, Timestamp endTimestamp) {
		return iOutstandingDao.getOutstandingwithCustomer(new Date(starTimestamp.getTime()) ,new Date(endTimestamp.getTime()));
	}
	
	}
