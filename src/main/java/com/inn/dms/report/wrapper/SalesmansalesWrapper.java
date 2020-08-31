package com.inn.dms.report.wrapper;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.salesman.model.Salesman;

public class SalesmansalesWrapper {
	
	private Billing billing;
	private Salesman salesman;
	public Billing getBilling() {
		return billing;
	}
	
	public SalesmansalesWrapper(Billing billing,Salesman salesman)
	{
		this.billing=billing;
		this.salesman=salesman;
	}
	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
}
