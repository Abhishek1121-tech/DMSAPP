package com.inn.dms.payments.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.dms.payments.model.Payment;

@Repository
public interface IPaymentDao extends JpaRepository<Payment, Long> {


}
