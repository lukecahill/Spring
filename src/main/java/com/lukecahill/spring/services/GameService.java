package com.lukecahill.spring.services;

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

    public GameViewModel add(Game game) {
        if(game == null) {
            throw new NullPointerException();
        }

        game.setCreated(new Timestamp(new Date().getTime()));
        gameRepository.save(game);

        return new GameViewModel(game);
    }

    public GameViewModel update(int gameId, Game game) {
        if(game == null) {
            throw new NullPointerException();
        }

        Game gameToUpdate = gameRepository.findOne(gameId);
        gameToUpdate.setName(game.getName());
        gameToUpdate.setPrice(game.getPrice());
        gameToUpdate.setPublisher(game.getPublisher());

        gameRepository.save(gameToUpdate);
        return new GameViewModel(game);
    }

    public void delete(int gameId) {
        if(gameId > 0) {
            gameRepository.delete(gameId);
        }
    }
}
