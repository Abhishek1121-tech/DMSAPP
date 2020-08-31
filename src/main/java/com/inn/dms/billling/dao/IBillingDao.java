package com.inn.dms.billling.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.report.wrapper.SalesmansalesWrapper;

@Repository
public interface IBillingDao extends JpaRepository<Billing, Long> {

	@Query("select new com.inn.dms.report.wrapper.SalesmansalesWrapper(item,cust.salesman) from Billing item left join item.customer cust join cust.salesman ")
	public List<SalesmansalesWrapper> findAllByCustomerNSalesman();
}
