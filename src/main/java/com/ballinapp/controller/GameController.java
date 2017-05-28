package com.ballinapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.data.PublicGame;
import com.ballinapp.data.Team;
import com.ballinapp.service.GameService;

@RestController
public class GameController {

    @Autowired
    private final GameService gameService = GameService.getInstance();

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public void createGame(@RequestBody PublicGame publicGame) {
        gameService.createGame(publicGame);
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List<PublicGame> getAllGames() {
        return gameService.getAllGames();
    }

    @RequestMapping(value = "/games/{city}", method = RequestMethod.GET)
    public List<PublicGame> findGamesByCity(@PathVariable String city) {
        return gameService.findGamesByCity(city);
    }

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.POST)
    public void joinGame(@PathVariable int gameId, @RequestBody Team team) {
        gameService.joinGame(gameId, team);
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public List<PublicGame> getGamesByTeam(@PathVariable Long id) {
        return gameService.getGamesByTeam(id);
    }

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.DELETE)
    public void leaveGame(@PathVariable int gameId, @RequestBody Team team) {
        gameService.leaveGame(gameId, team);
    }

}
