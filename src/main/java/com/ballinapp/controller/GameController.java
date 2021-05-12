package com.ballinapp.controller;

import java.util.List;

import com.ballinapp.data.info.PublicGameInfo;
import com.ballinapp.data.info.TeamInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.data.model.PublicGame;
import com.ballinapp.data.model.Team;
import com.ballinapp.exceptions.AuthenticationException;
import com.ballinapp.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class GameController {

	//singleton
    private final GameService gameService = GameService.getInstance();

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public ResponseEntity<Void> createGame(@RequestBody PublicGameInfo publicGame) {
        gameService.createGame(publicGame);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/games/{city}", method = RequestMethod.GET)
    public ResponseEntity<List<PublicGameInfo>> findGamesByCity(@PathVariable String city) {
        return new ResponseEntity<>(gameService.findGamesByCity(city), HttpStatus.OK);
    }

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.POST)
    public ResponseEntity<Void> joinGame(@PathVariable int gameId, @RequestBody TeamInfo team) {
        gameService.joinGame(gameId, team);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<PublicGameInfo>> getGamesByTeam(@PathVariable int id) {
        return new ResponseEntity<>(gameService.getGamesByTeam(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> leaveGame(@PathVariable int gameId, @RequestBody TeamInfo team) {
        gameService.leaveGame(gameId, team);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
