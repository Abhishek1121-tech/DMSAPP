package com.inn.dms.filetransict.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inn.dms.filetransict.service.IDocumentStorageService;

@RestController
public class FileUploadDownloadRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadDownloadRest.class);

	@Autowired
	IDocumentStorageService iDocumentStorageService;
	 
	 
	@GetMapping(path = "/downloadReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadReport(@RequestParam("filename") String fileName,
            HttpServletRequest request) {

       LOGGER.info("FILE NAME: {}",fileName);
        Resource resource = null;
        if(fileName !=null && !fileName.isEmpty()) {
        try {
            resource = iDocumentStorageService.loadFileAsResource(fileName);
            if (resource != null)
            {
            	LOGGER.info("Resource is not null {},{}",resource.getFile().getAbsolutePath(),resource.getFilename());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        String contentType = null;
        try {
        	 
           contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            LOGGER.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    } else {
        return ResponseEntity.notFound().build();
    }
    }

}
