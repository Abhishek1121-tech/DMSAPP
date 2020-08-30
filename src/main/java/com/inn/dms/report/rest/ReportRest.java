package com.inn.dms.report.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inn.dms.report.model.ReportEntry;
import com.inn.dms.report.service.IReportService;

@RestController
public class ReportRest {

	 private static final Logger LOGGER = LoggerFactory.getLogger(ReportRest.class);
	
	 @Autowired
	 private IReportService iReportService;
	 
	 @PostMapping(path = "/saveReportRequest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<String> saveReportRequest(@RequestBody ReportEntry reportEntry)
	{
		 String oString =iReportService.saveReportRequest(reportEntry);
		 if (oString!=null && oString.contains("bad_request"))
		 {
			 return new ResponseEntity<String>("Hi admin, report information is invalid, something wrong", HttpStatus.OK);
		 }else
		 {	 
		 return new ResponseEntity<String>("Hi admin, Report is successfully submitted with the Report Name "+oString+" in queue, Please check in the report list", HttpStatus.CREATED);
		}
	}
	 @GetMapping(path = "/findAllReportByPaginationSort" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<ReportEntry> findAllReportByPaginationSort(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "reportCreationDate") String sortBy)
		{
		 LOGGER.info("pageNo {}, pageSize {}, sortBy {}",pageNo,pageSize,sortBy);
			return iReportService.findAllByPagination(pageNo,pageSize,sortBy);
			
		}
	 
	 @GetMapping(path = "/findAllReports" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public Long findAllReports()
		{ 
			return iReportService.findAll();
			
		}
}
