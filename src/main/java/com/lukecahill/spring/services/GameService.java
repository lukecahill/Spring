package com.lukecahill.spring.services;

import com.lukecahill.spring.bindingmodels.GameBindingModel;
import com.lukecahill.spring.models.Game;
import com.lukecahill.spring.repositories.GameRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("gameService")
public class GameService {

    private GameRepository gameRepository;
    private EntityManager entityManager;

    @Inject
    public GameService(GameRepository gameRepository, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.gameRepository = gameRepository;
    }

    public Game get(int gameId) {
        return gameRepository.findOne(gameId);
    }

    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    public Game add(GameBindingModel.Create game) {
        if(game == null) {
            throw new NullPointerException();
        }

        Game gameToAdd = new Game(game.gameName, game.gamePublisher, game.gamePrice);
        gameToAdd.setCreated(new Timestamp(new Date().getTime()));
        gameRepository.save(gameToAdd);

        return gameToAdd;
    }

    public Game update(int gameId, GameBindingModel.Update game) {
        if(game == null) {
            throw new NullPointerException();
        }

        Game gameToUpdate = gameRepository.findOne(gameId);
        gameToUpdate.setName(game.gameName);
        gameToUpdate.setPrice(game.gamePrice);
        gameToUpdate.setPublisher(game.gamePublisher);

        gameRepository.save(gameToUpdate);
        return gameToUpdate;
    }

    public void delete(int gameId) {
        if(gameId > 0) {
            gameRepository.delete(gameId);
        }
    }
}
