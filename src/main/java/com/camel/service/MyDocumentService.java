package com.camel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camel.model.MyDocument;
import com.camel.repository.MyDocumentRepository;

@Service
public class MyDocumentService {
	
	private final MyDocumentRepository repository;
	
	@Autowired
    public MyDocumentService(MyDocumentRepository repository) {
        this.repository = repository;
    }

    public String insertDocument(MyDocument document) {
        MyDocument savedDocument = repository.save(document);
        return savedDocument.getId();
    }


}
