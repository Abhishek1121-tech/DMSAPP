package com.inn.dms.billling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.dms.billling.model.Billing;

@Repository
public interface IBillingDao extends JpaRepository<Billing, Long> {

}
