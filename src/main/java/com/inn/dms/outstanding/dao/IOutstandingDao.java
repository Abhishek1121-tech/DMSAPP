package com.inn.dms.outstanding.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inn.dms.outstanding.model.Oustanding;

import lombok.Synchronized;

public interface IOutstandingDao extends JpaRepository<Oustanding, Long> {
	
	@Query("SELECT count(*) from Oustanding where custAduitID = :id")
	public Integer checkCustomerEntryExists(Long id);
	
	@Query("SELECT o from Oustanding o where o.custAduitID = :id")
	public Oustanding getOutstandingAmountCustID(Long id);

}
