package com.webstock.mongodb.webstockmongodbspring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Companies")
public class Company {

    @Id
    public String id;
    private String acronym;
    private String name;
    private double price;
    private double todayPrice;
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTodayPrice() {
        return todayPrice;
    }

    public void setTodayPrice(double todayPrice) {
        this.todayPrice = todayPrice;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Companies{ " +
                "id=" + id +
                ", acronym='" + acronym + '\'' +
                ", companyName='" + name + '\'' +
                ", price='" + price + '\'' +
                ", todayPrice='" + todayPrice + '\'' +
                '}';
    }
}
