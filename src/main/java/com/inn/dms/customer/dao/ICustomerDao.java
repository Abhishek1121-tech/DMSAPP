package com.inn.dms.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inn.dms.customer.model.Customer;

public interface ICustomerDao extends JpaRepository<Customer, Long> {
	@Query("SELECT count(*) FROM Customer p WHERE p.mobile = :mobile")
	Integer verifyUserByMobileNumber(long mobile);

}
