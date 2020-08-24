package com.inn.dms.customer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inn.dms.customer.dao.ICustomerDao;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.customer.service.ICustomerService;
import com.inn.dms.salesman.dao.ISalesManDao;
import com.inn.dms.salesman.model.Salesman;
import com.inn.dms.salesman.service.impl.SalesmanServiceImpl;
@Component
public class CustomerServiceImpl implements ICustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	ISalesManDao iSalesManDao;
	
	@Autowired
	ICustomerDao iCustomerDao;
	@Override
	public String save(Customer customer,Long mobile) {
		
		Integer count_user = iCustomerDao.verifyUserByMobileNumber(customer.getMobile());
		LOGGER.info("Count is {}",count_user);
		if (count_user>0)
		{
			return "user_found";
		}
		Salesman salesman = iSalesManDao.getSalesmanIdByMobile(mobile);
		if (salesman != null)
		{
			Customer customer_save = new Customer();
			customer_save.setName(customer.getName());
			customer_save.setCreditLimit(customer.getCreditLimit());
			customer_save.setCustArea(customer.getCustArea());
			customer_save.setMobile(customer.getMobile());
			customer_save.setSalesman(salesman);
			customer=iCustomerDao.save(customer_save);
			return customer.getName();
		}
		else
		{
			return "salesman_not_exists";
		}
	}


}
