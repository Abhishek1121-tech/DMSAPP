package com.inn.dms.outstanding.service	;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.outstanding.model.Oustanding;

public interface IOutstandingService {
	
	public Oustanding persistOutstandingAmountSync(Customer customer, Billing billing);

}
