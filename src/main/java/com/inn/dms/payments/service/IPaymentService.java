package com.inn.dms.payments.service;

import com.inn.dms.payments.model.Payment;

public interface IPaymentService {
	
	public String save(Payment payment,Long mobile);
}
