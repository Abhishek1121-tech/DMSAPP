package com.inn.dms.report.service;

import java.util.List;

import com.inn.dms.report.model.ReportEntry;


public interface IReportService {
	
	public String saveReportRequest(ReportEntry reportEntry);
	
	public List<ReportEntry> findAllByPagination(Integer pageNo, Integer pageSize, String sortBy);
	
	public Long findAll();

}
