package com.inn.dms.report.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inn.dms.report.dao.IReportDao;
import com.inn.dms.report.model.ReportEntry;
import com.inn.dms.report.service.IReportService;

@Service
public class ReportServiceImpl implements IReportService {

	 private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Autowired
	private IReportDao iReportDao;
	
	@Override
	public String saveReportRequest(ReportEntry reportEntry) {
		if (reportEntry !=null)
		{
			Date date = new Date();
			ReportEntry reportEntry_save= new ReportEntry();
			reportEntry_save.setReportName(reportEntry.getReportType()+"-"+reportEntry.getReportSelectionType()+"-"+new Timestamp(date.getTime()).getTime());
			reportEntry_save.setReportSelectionType(reportEntry.getReportSelectionType());
			reportEntry_save.setReportType(reportEntry.getReportType());
			reportEntry_save.setReportStatus(reportEntry.getReportStatus());
			if (reportEntry.getReportSelectionType().equals("DATE_RANGE"))
			{
				LOGGER.info("reportEntry.getReportStartRangeTimestampInMs() {},,,, reportEntry.getReportEndRangeTimestampInMs() {}",reportEntry.getReportStartRangeTimestampInMs(),reportEntry.getReportEndRangeTimestampInMs());
				if ((reportEntry.getReportStartRangeTimestampInMs()==null) || (reportEntry.getReportEndRangeTimestampInMs()==null))
				{
					reportEntry_save.setReportStatus("INVAID REPORT");
				}else
				{
				reportEntry_save.setReportStartRangeTimestampInMs(reportEntry.getReportStartRangeTimestampInMs());
				reportEntry_save.setReportEndRangeTimestampInMs(reportEntry.getReportEndRangeTimestampInMs());
				}
			}
			reportEntry=iReportDao.save(reportEntry_save);	
			
			return reportEntry.getReportName();
		}
		else
		{
			return "bad_request";
		}
	}

	@Override
	public List<ReportEntry> findAllByPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable sortedByCreationTime = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		Page<ReportEntry> pagedResult = iReportDao.findAll(sortedByCreationTime);
		 if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<ReportEntry>();
	        }
	}

	@Override
	public Long findAll() {
		// TODO Auto-generated method stub
		return iReportDao.count();
	}
	
	

}
