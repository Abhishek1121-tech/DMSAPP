package com.inn.dms.outstanding.service	;

import com.inn.dms.outstanding.model.Oustanding;

public interface IOutstandingService {
	
	public Oustanding persistOutstandingAmountSync(long id, long paymentAmount,String type);

}
