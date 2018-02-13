package com.lukecahill.spring.viewmodels;

import com.lukecahill.spring.models.Game;

public class GameViewModel {

    private int gameId;
    private String gameName;
    private String gamePublisher;
    private double gamePrice;

    public GameViewModel() {}

    public GameViewModel(Game game) {
        this.gameId = game.getGameId();
        this.gameName = game.getName();
        this.gamePrice = game.getPrice();
        this.gamePublisher = game.getPublisher();
    }

    public GameViewModel(int gameId, String gameName, String gamePublisher, double gamePrice) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gamePublisher = gamePublisher;
        this.gamePrice = gamePrice;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGamePublisher() {
        return gamePublisher;
    }

    public void setGamePublisher(String gamePublisher) {
        this.gamePublisher = gamePublisher;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(double gamePrice) {
        this.gamePrice = gamePrice;
    }
}
