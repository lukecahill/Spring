package com.lukecahill.spring.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukecahill.spring.bindingmodels.GameBindingModel;
import com.lukecahill.spring.models.Game;
import com.lukecahill.spring.services.GameService;
import com.lukecahill.spring.viewmodels.GameViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/Games")
public class GameController {

    private GameService gameService;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    public GameController() {}

    @Inject
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{gameId}")
    public ResponseEntity<?> get(@PathVariable int gameId) {
        return ResponseEntity.ok(new GameViewModel(gameService.get(gameId)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(gameService.getAll().stream().map(x -> new GameViewModel(x)).collect(Collectors.toCollection(ArrayList::new)));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{gameId}")
    public ResponseEntity<?> add(@PathVariable int gameId, @RequestBody @Valid GameBindingModel.Create game, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        GameViewModel gameViewModel = new GameViewModel(gameService.add(game));
        return ResponseEntity.ok(gameViewModel);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{gameId}")
    public ResponseEntity<?> update(@PathVariable int gameId, @RequestBody @Valid GameBindingModel.Update game, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        return ResponseEntity.ok(new GameViewModel(gameService.update(gameId, game)));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{gameId}")
    public ResponseEntity<?> delete(@PathVariable int gameId, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        gameService.delete(gameId);
        return ResponseEntity.ok().build();
    }
}
