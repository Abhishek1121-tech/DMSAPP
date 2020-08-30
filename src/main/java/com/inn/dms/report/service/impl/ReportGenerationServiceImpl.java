package com.inn.dms.report.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import com.inn.dms.report.dao.IReportGenerationDao;
import com.inn.dms.report.model.ReportEntry;
import com.inn.dms.report.service.IReportGenerationService;
import com.inn.dms.report.utils.ReportMultiThreadedInstnace;
import com.inn.dms.report.utils.Reportutils;
@Service
public class ReportGenerationServiceImpl implements IReportGenerationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportGenerationServiceImpl.class);
	
	@Autowired
	private IReportGenerationDao iReportGenerationDao;
	@Autowired
	private ApplicationContext applicationContext;
	
	private ExecutorService executor;
	
	
	@Override
	public void executeRequestedReportCreation() {
		executor = Executors.newFixedThreadPool(Reportutils.REPORTTHREADS,new CustomizableThreadFactory("reportGeneration-"));
		LOGGER.info("Let's Go for Report Creation : {}", new Date());
		Pageable pageable = PageRequest.of(Reportutils.pagenoProccessedInOnce, Reportutils.reportSizeProccessedInOnce);
		List<ReportEntry> reportEntries = iReportGenerationDao.getAllQueuedReportLists("QUEUED", pageable);
		LOGGER.info("Report List Size {}",reportEntries.size());
		if (reportEntries.size() >0)
		{
		for (ReportEntry reportEntry : reportEntries)
		{
			Runnable worker = new ReportMultiThreadedInstnace(reportEntry);
			applicationContext.getAutowireCapableBeanFactory().autowireBean(worker);
			executor.execute(worker);
		}
		executor.shutdown();
		// Wait until all threads are workerfinish
		while (!executor.isTerminated()) {
			
		}
		LOGGER.info("Finshed all threads");
		}
		else
		{
			LOGGER.info("I am relaxed mode, No reports, No Work found in queue");
		}
		
	}

}
