package com.webstock.mongodb.webstockmongodbspring.controller;

import com.webstock.mongodb.webstockmongodbspring.model.Company;
import com.webstock.mongodb.webstockmongodbspring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:3000")
@Component
public class CompanyController {

    private boolean newDay = true;

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Company add (@RequestBody Company company){
        return companyRepository.save(company);
    }

    @GetMapping
    public List<Company> getAll(){
        return companyRepository.findAll();
    }


    //@Scheduled(fixedRate = 5000)
    public void pickRandomCompany(){

        Random random = new Random();
        int index = random.nextInt(15);
        Company company = companyRepository.findAll().get(index);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH");
        String day = format.format(date);

        if(!day.equals("00")) {
            newDay = true;
            System.out.println("NAME OF COMPANY UPDATE: "+company.getName());
            updatePrice(company);
        }else if(newDay){
            newDay = false;
            System.out.println("UPDATING PRICE");
            newDayUpdate();
        }else{
            System.out.println("SOCK MARKET CLOSED");
        }
    }

    public void updatePrice(Company company) {

        Random random = new Random();

        int postiveOrNegative = random.nextInt(2) == 1 ? -1 : 1;

        double change = postiveOrNegative * Math.round((random.nextDouble()*10) * 100.0)/100.0;
        change = company.getPrice() + change < 0 ? change*-1: change;

        double price = company.getPrice() + change;
        price = Math.round(price * 100.0)/100.0;

        company.setPrice(price);

        companyRepository.save(company);
    }

    public void newDayUpdate(){

        Company company;

        for(int i=0; i<companyRepository.count(); i++){
            company = companyRepository.findAll().get(i);
            company.setTodayPrice(company.getPrice());
//            company.setChange(0.0);
//            company.setChg(0.0);
            companyRepository.save(company);
        }
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        companyRepository.delete(company);
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException() {
        }
    }
}
