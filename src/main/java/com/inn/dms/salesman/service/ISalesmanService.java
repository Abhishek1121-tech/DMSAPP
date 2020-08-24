package com.inn.dms.salesman.service;

import java.util.List;

import com.inn.dms.salesman.model.Salesman;
import com.inn.dms.salesman.wrapper.SalesManMobileWrapper;

public interface ISalesmanService {

	public String save(Salesman salesman);
	public List<SalesManMobileWrapper> findAllSalesmanByMobile();
	public List<Long> searchSalesmanByMobile(Long mobile);
	
}
