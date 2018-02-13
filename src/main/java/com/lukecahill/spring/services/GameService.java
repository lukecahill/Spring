package com.lukecahill.spring.services;

import com.lukecahill.spring.models.Game;
import com.lukecahill.spring.repositories.GameRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
        return null;
    }

    public Game add(Game game) {
        return game;
    }

    public Game update(Game game) {
        return game;
    }

    public void delete(int gameId) {
        return;
    }
}
