package com.inn.dms.filetransict.service;

import org.springframework.core.io.Resource;

public interface IDocumentStorageService {

	public Resource loadFileAsResource(String fileName) throws Exception;
}
