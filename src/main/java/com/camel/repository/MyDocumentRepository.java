package com.camel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.camel.model.MyDocument;

public interface MyDocumentRepository extends MongoRepository<MyDocument, String>{

}
