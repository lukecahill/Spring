package com.lukecahill.spring.bindingmodels;

import javax.validation.constraints.NotNull;

public class GameBindingModel {

    public static class Create {
        @NotNull
        public String gameName;
        @NotNull
        public String gamePublisher;
        @NotNull
        public double gamePrice;

        public Create() {}

        public Create(String gameName, String gamePublisher, double gamePrice) {
            this.gameName = gameName;
            this.gamePublisher = gamePublisher;
            this.gamePrice = gamePrice;
        }
    }

    public static class Update {

        @NotNull
        public String gameName;
        @NotNull
        public String gamePublisher;
        @NotNull
        public double gamePrice;
        @NotNull
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
