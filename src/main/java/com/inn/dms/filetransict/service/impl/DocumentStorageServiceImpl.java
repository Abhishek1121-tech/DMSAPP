package com.inn.dms.filetransict.service.impl;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.inn.dms.filetransict.service.IDocumentStorageService;
import com.inn.dms.report.utils.Reportutils;

@Service
public class DocumentStorageServiceImpl implements IDocumentStorageService {

	 private static final Logger LOGGER = LoggerFactory.getLogger(DocumentStorageServiceImpl.class);

	@Override
	public Resource loadFileAsResource(String fileName) throws Exception {
		 try {
			  
	            Path filePath = Paths.get(Reportutils.REPORT_URL_BASE_PATH+fileName).resolve(Reportutils.REPORT_URL_BASE_PATH+fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new FileNotFoundException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new FileNotFoundException("File not found " + fileName);
	        }   
	}
    
    

}
