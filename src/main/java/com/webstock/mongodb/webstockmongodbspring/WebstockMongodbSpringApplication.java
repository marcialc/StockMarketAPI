package com.webstock.mongodb.webstockmongodbspring;

import com.webstock.mongodb.webstockmongodbspring.controller.CompanyController;
import com.webstock.mongodb.webstockmongodbspring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebstockMongodbSpringApplication implements CommandLineRunner {

	@Autowired
	private CompanyRepository companyRepository;
	private CompanyController companyController;

	public static void main(String[] args) {
		SpringApplication.run(WebstockMongodbSpringApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println(companyRepository.findAll());
	}

}
