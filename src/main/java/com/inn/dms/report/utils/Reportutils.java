package com.inn.dms.report.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inn.dms.billling.model.Billing;
import com.inn.dms.customer.model.Customer;
import com.inn.dms.expense.model.Expense;
import com.inn.dms.outstanding.model.Oustanding;
import com.inn.dms.report.model.ReportEntry;
import com.inn.dms.report.wrapper.OutsatndingCustomerWrapper;
import com.inn.dms.report.wrapper.SalesmansalesWrapper;
import com.inn.dms.salesman.model.Salesman;

public class Reportutils {

	private static final Logger LOGGER = LoggerFactory.getLogger(Reportutils.class);

	public static Integer reportSizeProccessedInOnce = 5;
	public static Integer pagenoProccessedInOnce = 0;
	public static final int REPORTTHREADS = 5;
	public static String DASH = "-";
	public static String DATE_TIME_UTC="yyyy-MM-dd'T'HH:mm:ss.SZ";

	public static String ALL_SELECTION = "ALL";
	public static String DATE_RANGE_SELECTION = "DATE_RANGE";
	public static String REPORT_IN_PROGRESS="IN_PROGRESS";
	public static String REPORT_CREATION_ERROR="report_creation_error";
	public static String REPORT_PATH_CREATION_ERROR="report_path_creation_error";
	public static String CONST_COMPLETED="COMPLETED";
	public static String CONST_FAILED="FAILED";
	
	public static String REPORT_URL_BASE_PATH="/home/ist";
	public static String REPORT_URL_BASE_PATH_PREFFIX="REPORTS";
	public static String FORWARD_SLASH="/";
	public static String DOT=".";
	public static String XLSX_EXT="xlsx";

	public static void convertObjectToExcelFile(Collection<? extends Object> object, ReportEntry reportEntry,
			String sheetName,Map<String, Object[]> data) throws IOException {
		LOGGER.info("Conversion for Objct to Excel Report name {}, Sheet name {}", reportEntry.getReportName(), sheetName);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(sheetName);
		XSSFRow row = null;

		if (object instanceof List<?>) {
			if(((List<?>)object).size()>0){
			persistSaveFileReporting(workbook, spreadsheet, row, data,reportEntry);
			}else {
				LOGGER.info("List is empty for Report Object");
			}
		}

	}

	private static void persistSaveFileReporting(XSSFWorkbook workbook, XSSFSheet spreadsheet, XSSFRow row,Map<String, Object[]> data,ReportEntry reportEntry) throws IOException {
		
		  SimpleDateFormat timeFormat = new SimpleDateFormat(Reportutils.DATE_TIME_UTC);
		//Iterate over data and write to sheet
	        Set<String> keyset = data.keySet();
	        int rownum = 0;
	        for (String key : keyset)
	        {
	            row = spreadsheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Long)
	                    cell.setCellValue((Long)obj);
	                else if (obj instanceof Timestamp)
	                	cell.setCellValue(timeFormat.format(obj));
	                else if (obj instanceof Integer)
	                	cell.setCellValue((Integer)obj);
	            }
	        }
	        
	        	try {
					//Write the workbook in file system
					FileOutputStream out = new FileOutputStream(new File(Reportutils.REPORT_URL_BASE_PATH+reportEntry.getReportDownloadURL()));
					workbook.write(out);
					out.close();
				} catch (SecurityException e) {
					e.printStackTrace();
					LOGGER.error("Exception in creating path Report Name {}",reportEntry.getReportName());
		            throw new SecurityException(Reportutils.REPORT_PATH_CREATION_ERROR);
				}
	        	catch (IOException e) {
					e.printStackTrace();
					LOGGER.error("Exception in creating excel file for Report Name {}",reportEntry.getReportName());
		            throw new IOException(Reportutils.REPORT_CREATION_ERROR);
				}	  
	}
	
	public static void createDirectoryIfNotExist(String path) throws IOException
	{
		 Path path_toBeCreatd = Paths.get(path);
		 if (!Files.exists(path_toBeCreatd)) {
	            Files.createDirectories(path_toBeCreatd);
	            LOGGER.info("Path created Successfully {}",path);
	        } else {
	        	  LOGGER.info("Path Already Exists {}  ",path);
	        }
	}

}