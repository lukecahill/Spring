package com.lukecahill.spring.services;

import com.lukecahill.spring.bindingmodels.GameBindingModel;
import com.lukecahill.spring.models.Game;
import com.lukecahill.spring.repositories.GameRepository;
import com.lukecahill.spring.viewmodels.GameViewModel;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("gameService")
public class GameService {

    private GameRepository gameRepository;
    private EntityManager entityManager;

    @Inject
    public GameService(GameRepository gameRepository, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.gameRepository = gameRepository;
    }

    public GameViewModel get(int gameId) {
        return new GameViewModel(gameRepository.findOne(gameId));
    }

    public List<GameViewModel> getAll() {
        return gameRepository.findAll().stream().map(x -> new GameViewModel(x))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public GameViewModel add(GameBindingModel.Create game) {
        if(game == null) {
            throw new NullPointerException();
        }

        Game gameToAdd = new Game(game.gameName, game.gamePublisher, game.gamePrice);
        gameToAdd.setCreated(new Timestamp(new Date().getTime()));
        gameRepository.save(gameToAdd);

        return new GameViewModel(gameToAdd);
    }

    public GameViewModel update(int gameId, GameBindingModel.Update game) {
        if(game == null) {
            throw new NullPointerException();
        }

        Game gameToUpdate = gameRepository.findOne(gameId);
        gameToUpdate.setName(game.gameName);
        gameToUpdate.setPrice(game.gamePrice);
        gameToUpdate.setPublisher(game.gamePublisher);

        gameRepository.save(gameToUpdate);
        return new GameViewModel(gameToUpdate);
    }

    public void delete(int gameId) {
        if(gameId > 0) {
            gameRepository.delete(gameId);
        }
    }
}
