package com.inn.dms.outstanding.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inn.dms.outstanding.dao.IOutstandingDao;
import com.inn.dms.outstanding.model.Oustanding;
import com.inn.dms.outstanding.service.IOutstandingService;

@Component
public class OutstandingServiceImpl implements IOutstandingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OutstandingServiceImpl.class);
	
	@Autowired
	private IOutstandingDao iOutstandingDao;

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	public synchronized Oustanding persistOutstandingAmountSync(long id, long amount,String type) {
		LOGGER.info("hey hurray we are in synchronized block, id {} amount {} type {}",id,amount,type);
		Integer checkFlagForExistnace = iOutstandingDao.checkCustomerEntryExists(id);
		Oustanding oustanding = new Oustanding();
		if (checkFlagForExistnace > 0)
		{
			oustanding=iOutstandingDao.getOutstandingAmountCustID(id);
			if (type.equals("payment"))
			{
				oustanding.setOutstandingAmount(oustanding.getOutstandingAmount()-amount);
			}else if (type.equals("billing")) {
				oustanding.setOutstandingAmount(oustanding.getOutstandingAmount()+amount);
			}else
			{
				LOGGER.info("No known or Null type specified {}",type);
			}
		}else {
			oustanding.setCustAduitID(id);
			if (type.equals("payment"))
			{
				oustanding.setOutstandingAmount(0-amount);
			}else if (type.equals("billing")) {
				oustanding.setOutstandingAmount(0+amount);
			}else
			{
				LOGGER.info("No known or Null type specified {}",type);
			}		
		}
		oustanding.setTransactionType(type);
		oustanding=iOutstandingDao.save(oustanding);
		return oustanding;
		}
	}
