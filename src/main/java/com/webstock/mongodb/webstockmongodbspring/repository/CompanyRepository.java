package com.webstock.mongodb.webstockmongodbspring.repository;

import com.webstock.mongodb.webstockmongodbspring.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "company", path = "company")
@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {


}
