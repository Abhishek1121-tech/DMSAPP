package com.inn.dms.report.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.inn.dms.report.service.IReportGenerationService;
@Service
public class ReportGenerationServiceImpl implements IReportGenerationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportGenerationServiceImpl.class);
	
	@Override
	public void executeRequestedReportCreation() {
		LOGGER.info("Hello Time: {}", new Date());
	}

}
