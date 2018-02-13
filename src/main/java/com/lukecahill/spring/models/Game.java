package com.lukecahill.spring.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Games")
public class Game extends BaseModel {

    private String name;
    private String publisher;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
