package com.inn.dms.report.utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inn.dms.billling.service.IBillingService;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.customer.service.ICustomerService;
import com.inn.dms.expense.model.Expense;
import com.inn.dms.expense.service.IExpenseService;
import com.inn.dms.outstanding.service.IOutstandingService;
import com.inn.dms.report.dao.IReportDao;
import com.inn.dms.report.model.ReportEntry;
import com.inn.dms.report.wrapper.OutsatndingCustomerWrapper;
import com.inn.dms.report.wrapper.SalesmansalesWrapper;
import com.inn.dms.salesman.model.Salesman;
import com.inn.dms.salesman.service.ISalesmanService;

public class ReportMultiThreadedInstnace implements Runnable{

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportMultiThreadedInstnace.class);

	private ReportEntry reportEntry;
	
	@Autowired
	private IExpenseService iExpenseService;
	
	@Autowired
    private ISalesmanService iSalesmanService;
	
	@Autowired
	private ICustomerService iCustomerService;
	
	@Autowired
	private IBillingService iBillingService;
	
	@Autowired
	private IOutstandingService iOutstandingService;
	
	@Autowired
	private IReportDao iReportDao;
	 
	
	
	public ReportMultiThreadedInstnace(ReportEntry reportEntry){
		this.reportEntry=reportEntry;
	}
	
	@Override
	public void run() {
		this.reportEntry.setReportStatus(Reportutils.REPORT_IN_PROGRESS);
		this.reportEntry=iReportDao.save(this.reportEntry);	
		try {
			checkReportType(this.reportEntry);
		} catch (Exception e) {
			LOGGER.info("Exception while running job for report Name {}",reportEntry.getReportName());
			this.reportEntry.setFailedReason(e.getMessage());
			this.reportEntry.setReportStatus(Reportutils.CONST_FAILED);
			this.reportEntry=iReportDao.save(this.reportEntry);
		}
		
	}

	private void checkReportType(ReportEntry reportEntry) {
		 switch(reportEntry.getReportType()) 
	        { 
	            case "EXPENSE": 
	                generateReportExpense(iExpenseService,reportEntry); 
	                break; 
	            case "SALESMAN": 
	                generateReportSalesman(iSalesmanService,reportEntry); 
	                break; 
	            case "CUSTOMER": 
	                generateReportCustomer(iCustomerService,reportEntry); 
	                break; 
	            case "OUSTANDING": 
	                generateReportOustanding(iOutstandingService,reportEntry); 
	                break;
	            case "SLAESMAN_SALE": 
	                generateReportSalesmanSale(iBillingService,reportEntry); 
	                break;
	            default: 
	            	LOGGER.info("Report Type not matched");
	        } 
		
	}

	private void generateReportSalesmanSale(IBillingService retrieveService, ReportEntry reportEntry) {
		LOGGER.info("reportName {} reportSelection {}",reportEntry.getReportName(),reportEntry.getReportSelectionType());
		Object[] headerList=new Object[] {"BILLING ID", "BILLING AMOUNT", "REMARKS","TRANSACTION DATE","TRANSACTION TYPE","CUSTOMER ID","SALESMAN ID"};
		Map<String, Object[]> data =new TreeMap<String, Object[]>();
		int rowNum=0;
		rowNum++;
		data.put(String.valueOf(rowNum),headerList);
		
		if (reportEntry.getReportSelectionType().equals(Reportutils.ALL_SELECTION))
		{
			List<SalesmansalesWrapper> listObject =retrieveService.findAllByCustomerNSalesman();
			for (SalesmansalesWrapper salesmansalesWrapper: listObject)
			{
				rowNum++;
				data.put(String.valueOf(rowNum), new Object[] {salesmansalesWrapper.getBilling().getId(),salesmansalesWrapper.getBilling().getBillAmount(),salesmansalesWrapper.getBilling().getDescriptionRemarks(),salesmansalesWrapper.getBilling().getTransactionDate(),salesmansalesWrapper.getBilling().getTransactionType(),salesmansalesWrapper.getBilling().getCustomer().getId(),salesmansalesWrapper.getSalesman_id_pk()});
			}	
			handlingExceptionForFailedReportCreation(listObject, this.reportEntry, "Expense", data);
		}else if (reportEntry.getReportSelectionType().equals(Reportutils.DATE_RANGE_SELECTION)) {
			
		}else {
			LOGGER.info("No Selection Type matched");
		}
	}

	private void generateReportOustanding(IOutstandingService retrieveService, ReportEntry reportEntry) {
		LOGGER.info("reportName {} reportSelection {}",reportEntry.getReportName(),reportEntry.getReportSelectionType());
		Object[] headerList=new Object[] {"OUTSATNDING ID", "CUSTOMER NAME","CUSTOMER MOBILE", "OUTSTANDING AMOUNT","LAST TRANSACTION DATE","LAST TRANSACTION AMOUNT","LAST TRANSACTION TYPE"};
		Map<String, Object[]> data =new TreeMap<String, Object[]>();
		int rowNum=0;
		rowNum++;
		data.put(String.valueOf(rowNum),headerList);
		
		if (reportEntry.getReportSelectionType().equals(Reportutils.ALL_SELECTION))
		{
			List<OutsatndingCustomerWrapper> listObject =retrieveService.getOutstandingwithCustomer();
			for (OutsatndingCustomerWrapper outsatndingCustomerWrapper : listObject)
			{
				rowNum++;
				data.put(String.valueOf(rowNum), new Object[] {outsatndingCustomerWrapper.getOustanding().getId(),outsatndingCustomerWrapper.getCustomer().getName(),outsatndingCustomerWrapper.getCustomer().getMobile(),outsatndingCustomerWrapper.getOustanding().getOutstandingAmount(),outsatndingCustomerWrapper.getOustanding().getLastTransactionDate(),outsatndingCustomerWrapper.getOustanding().getLastTransactionAmount(),outsatndingCustomerWrapper.getOustanding().getLastTransactionType()});
			}	
		
			handlingExceptionForFailedReportCreation(listObject, this.reportEntry, "Outstanding", data);	
				
			
		}else if (reportEntry.getReportSelectionType().equals(Reportutils.DATE_RANGE_SELECTION)) {
			
		}else {
			LOGGER.info("No Selection Type matched");
		}		
	}

	private void generateReportSalesman(ISalesmanService retrieveService, ReportEntry reportEntry) {
		LOGGER.info("reportName {} reportSelection {}",reportEntry.getReportName(),reportEntry.getReportSelectionType());
		Object[] headerList=new Object[] {"SALESMAN ID", "NAME", "MOBILE","JOINNING DATE","TARGETS"};
		Map<String, Object[]> data =new TreeMap<String, Object[]>();
		int rowNum=0;
		rowNum++;
		data.put(String.valueOf(rowNum),headerList);
		
		if (reportEntry.getReportSelectionType().equals(Reportutils.ALL_SELECTION))
		{
			List<Salesman> listObject =retrieveService.findAll();
			for (Salesman salesman : listObject)
			{
				rowNum++;
				data.put(String.valueOf(rowNum), new Object[] {salesman.getId(),salesman.getName(),salesman.getMobile(),salesman.getJoiningDate(),salesman.getTarget()});
			}	
			handlingExceptionForFailedReportCreation(listObject, this.reportEntry, "Expense", data);
		}else if (reportEntry.getReportSelectionType().equals(Reportutils.DATE_RANGE_SELECTION)) {
			
		}else {
			LOGGER.info("No Selection Type matched");
		}
		
	}

	private void generateReportCustomer(ICustomerService retrieveService, ReportEntry reportEntry) {
		LOGGER.info("reportName {} reportSelection {}",reportEntry.getReportName(),reportEntry.getReportSelectionType());
		Object[] headerList=new Object[] {"CUSTOMER ID", "NAME", "MOBILE","AREA","JOINING DATE","CREDIT LIMIT","SALESMAN ID"};
		Map<String, Object[]> data =new TreeMap<String, Object[]>();
		int rowNum=0;
		rowNum++;
		data.put(String.valueOf(rowNum),headerList);
		
		if (reportEntry.getReportSelectionType().equals(Reportutils.ALL_SELECTION))
		{
			List<Customer> listObject =retrieveService.findAll();
			for (Customer customer : listObject)
			{
				rowNum++;
				data.put(String.valueOf(rowNum), new Object[] {customer.getId(),customer.getName(),customer.getMobile(),customer.getCustArea(),customer.getJoiningDate(),customer.getCreditLimit(),customer.getSalesman().getId()});
			}	
			handlingExceptionForFailedReportCreation(listObject, this.reportEntry, "Customer", data);
		}else if (reportEntry.getReportSelectionType().equals(Reportutils.DATE_RANGE_SELECTION)) {
			
		}else {
			LOGGER.info("No Selection Type matched");
		}
		
	}

	private void generateReportExpense(IExpenseService retrieveService,  ReportEntry reportEntry) {
		LOGGER.info("reportName {} reportSelection {}",reportEntry.getReportName(),reportEntry.getReportSelectionType());
		Object[] headerList=new Object[] {"EXPENSE ID", "EXPENSE AMOUNT", "EXPENSE TYPE","EXPENSE DATE","REMARKS"};
		Map<String, Object[]> data =new TreeMap<String, Object[]>();
		int rowNum=0;
		rowNum++;
		data.put(String.valueOf(rowNum),headerList);
		
		if (reportEntry.getReportSelectionType().equals(Reportutils.ALL_SELECTION))
		{
			List<Expense> listObject =retrieveService.findAll();
			for (Expense expense : listObject)
			{
				rowNum++;
				data.put(String.valueOf(rowNum), new Object[] {expense.getId(),expense.getExpenseAmount(),expense.getExpenseType(),expense.getExpenseDate(),expense.getDescriptionRemarks()});
			}	
			handlingExceptionForFailedReportCreation(listObject, this.reportEntry, "Expense", data);
		}else if (reportEntry.getReportSelectionType().equals(Reportutils.DATE_RANGE_SELECTION)) {
			
		}else {
			LOGGER.info("No Selection Type matched");
		}
		
	}
	
	public void handlingExceptionForFailedReportCreation(Collection<? extends Object> object, ReportEntry reportEntry,
			String sheetName,Map<String, Object[]> data) {
		try {
			Date date = new Date();
			String pathToBeCreated=Reportutils.FORWARD_SLASH+Reportutils.REPORT_URL_BASE_PATH_PREFFIX+Reportutils.FORWARD_SLASH+reportEntry.getReportType()+Reportutils.FORWARD_SLASH+new Timestamp(date.getTime()).getTime()+Reportutils.FORWARD_SLASH;
			Reportutils.createDirectoryIfNotExist(Reportutils.REPORT_URL_BASE_PATH+pathToBeCreated);
			reportEntry.setReportDownloadURL(pathToBeCreated+reportEntry.getReportName()+Reportutils.DOT+Reportutils.XLSX_EXT);
			Reportutils.convertObjectToExcelFile(object, this.reportEntry, sheetName, data);
			reportEntry.setReportStatus(Reportutils.CONST_COMPLETED);
			this.reportEntry=iReportDao.save(reportEntry);
			LOGGER.info("Report Generation succeeded Report Name {},",this.reportEntry.getReportName());
		} catch (IOException e) {
			if (e.getMessage().equals(Reportutils.REPORT_PATH_CREATION_ERROR))
			{
				LOGGER.info("Report Generation Failed, Due to Not access of path creationReport Name {},",reportEntry.getReportName());
				reportEntry.setFailedReason(Reportutils.REPORT_PATH_CREATION_ERROR);
				reportEntry.setReportStatus(Reportutils.CONST_FAILED);
				this.reportEntry=iReportDao.save(reportEntry);
			}else
			if (e.getMessage().equals(Reportutils.REPORT_CREATION_ERROR))
			{
				LOGGER.info("Report Generation Failed Report Name {},",reportEntry.getReportName());
				reportEntry.setFailedReason(Reportutils.REPORT_CREATION_ERROR);
				reportEntry.setReportStatus(Reportutils.CONST_FAILED);
				this.reportEntry=iReportDao.save(reportEntry);
			}
	}
	}
}
