package com.inn.dms.salesman.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inn.dms.salesman.dao.ISalesManDao;
import com.inn.dms.salesman.model.Salesman;
import com.inn.dms.salesman.service.ISalesmanService;
import com.inn.dms.salesman.wrapper.SalesManMobileWrapper;

@Component
public class SalesmanServiceImpl implements ISalesmanService {

	 private static final Logger LOGGER = LoggerFactory.getLogger(SalesmanServiceImpl.class);
	 
	@Autowired
	private ISalesManDao iSalesManDao;

	@Override
	public String save(Salesman salesman) {
		
		Integer count_user = iSalesManDao.verifyUserByMobileNumber(salesman.getMobile());
		LOGGER.info("Count is {}",count_user);
		if (count_user>0)
		{
			return "exists";
		}else
		{
		Salesman salesman_save = new Salesman();
		salesman_save.setName(salesman.getName());
		salesman_save.setMobile(salesman.getMobile());
		salesman_save.setTarget(salesman.getTarget());
		salesman = iSalesManDao.save(salesman_save);
		return salesman.getName();
		}
	}

	@Override
	public List<SalesManMobileWrapper> findAllSalesmanByMobile() {
		// TODO Auto-generated method stub
		return iSalesManDao.getSalesmanIdByMobile();
	}

	@Override
	public List<Long> searchSalesmanByMobile(Long mobile) {
		// TODO Auto-generated method stub
		return iSalesManDao.searchSalesmanIdByMobile(mobile);
	}
	
	
}
