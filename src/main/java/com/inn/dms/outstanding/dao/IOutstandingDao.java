package com.inn.dms.outstanding.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inn.dms.outstanding.model.Oustanding;
import com.inn.dms.report.wrapper.OutsatndingCustomerWrapper;

public interface IOutstandingDao extends JpaRepository<Oustanding, Long> {
	
	@Query("SELECT count(*) from Oustanding where custAduitID = :id")
	public Integer checkCustomerEntryExists(Long id);
	
	@Query("SELECT o from Oustanding o where o.custAduitID = :id")
	public Oustanding getOutstandingAmountCustID(Long id);
	
	@Query("SELECT new com.inn.dms.report.wrapper.OutsatndingCustomerWrapper(o,c) from Oustanding o , Customer c where o.custAduitID = c.id")
	public List<OutsatndingCustomerWrapper> getOutstandingwithCustomer();
	@Query("SELECT new com.inn.dms.report.wrapper.OutsatndingCustomerWrapper(o,c) from Oustanding o   , Customer c where o.custAduitID = c.id AND date(o.lastTransactionDate) BETWEEN :starTimestamp AND :endTimestamp ")
	public List<OutsatndingCustomerWrapper> getOutstandingwithCustomer(@Param("starTimestamp") Date startDate, @Param("endTimestamp") Date endDate);

}
