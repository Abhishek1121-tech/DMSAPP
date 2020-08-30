package com.inn.dms.report.wrapper;

import com.inn.dms.billling.model.Billing;

public class SalesmansalesWrapper {
	
	private Billing billing;
	private Long salesman_id_pk;
	public Billing getBilling() {
		return billing;
	}
	
	public SalesmansalesWrapper(Billing billing,Long salesman_id_pk)
	{
		this.billing=billing;
		this.salesman_id_pk=salesman_id_pk;
	}
	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public Long getSalesman_id_pk() {
		return salesman_id_pk;
	}

	public void setSalesman_id_pk(Long salesman_id_pk) {
		this.salesman_id_pk = salesman_id_pk;
	}
	
	
	

}
