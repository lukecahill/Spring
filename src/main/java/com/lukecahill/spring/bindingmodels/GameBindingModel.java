package com.lukecahill.spring.bindingmodels;

import org.hibernate.validator.constraints.NotBlank;

public class GameBindingModel {

    public static class Create {
        @NotBlank
        public String gameName;
        @NotBlank
        public String gamePublisher;
        @NotBlank
        public double gamePrice;

        public Create() {}

        public Create(String gameName, String gamePublisher, double gamePrice) {
            this.gameName = gameName;
            this.gamePublisher = gamePublisher;
            this.gamePrice = gamePrice;
        }
    }

    public static class Update {

        @NotBlank
        public String gameName;
        @NotBlank
        public String gamePublisher;
        @NotBlank
        public double gamePrice;
        @NotBlank
        public int gameId;

        public Update() {}

        public Update(String gameName, String gamePublisher, double gamePrice, int gameId) {
            this.gameName = gameName;
            this.gamePublisher = gamePublisher;
            this.gamePrice = gamePrice;
            this.gameId = gameId;
        }
    }
}
