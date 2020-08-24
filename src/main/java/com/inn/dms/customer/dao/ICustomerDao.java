package com.inn.dms.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inn.dms.billling.warpper.CustomerMobileWrapper;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.salesman.model.Salesman;

public interface ICustomerDao extends JpaRepository<Customer, Long> {
	@Query("SELECT count(*) FROM Customer p WHERE p.mobile = :mobile")
	Integer verifyUserByMobileNumber(long mobile);
	
	@Query("SELECT c FROM Customer c WHERE c.mobile = :mobile")
	public Customer getCustomerIdByMobile(@Param("mobile")Long mobile);

	@Query("SELECT new com.inn.dms.billling.warpper.CustomerMobileWrapper(name,mobile)   FROM Customer ")
	public List<CustomerMobileWrapper> getCustomerIdByMobile();
	
	@Query("SELECT new com.inn.dms.billling.warpper.CustomerMobileWrapper(name,mobile) FROM Customer where str(mobile) like CONCAT(:mobile, '%')")
	public List<CustomerMobileWrapper> searchCustomerIdByMobile(@Param("mobile")Long mobile);
}
