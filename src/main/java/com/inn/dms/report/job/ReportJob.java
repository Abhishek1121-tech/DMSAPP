package com.inn.dms.report.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inn.dms.report.service.IReportGenerationService;
import com.inn.dms.report.service.impl.ReportGenerationServiceImpl;

public class ReportJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportJob.class);

	@Autowired
	private IReportGenerationService iReportService;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		iReportService.executeRequestedReportCreation();
		
	}

}
