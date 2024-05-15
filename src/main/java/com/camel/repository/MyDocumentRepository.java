package com.camel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.camel.model.MyDocument;

@Repository
public interface MyDocumentRepository extends MongoRepository<MyDocument, String>{

}
