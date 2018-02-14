package com.lukecahill.spring.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "Games")
public class Game extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;
    @NotBlank
    @Length(min = 1)
    private String name;
    @NotBlank
    @Length(min = 1)
    private String publisher;
    private double price;

    public Game() {}

    public Game(String name, String publisher, double price) {
        this.name = name;
        this.publisher = publisher;
        this.price = price;
    }

    public Game(int gameId, String name, String publisher, double price) {
        this.gameId = gameId;
        this.name = name;
        this.publisher = publisher;
        this.price = price;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

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
