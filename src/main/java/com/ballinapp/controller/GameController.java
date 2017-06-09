package com.ballinapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.data.PublicGame;
import com.ballinapp.data.Team;
import com.ballinapp.exceptions.AuthenticationException;
import com.ballinapp.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class GameController {

    @Autowired
    private final GameService gameService = GameService.getInstance();

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public ResponseEntity<Void> createGame(@RequestBody PublicGame publicGame, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            gameService.createGame(publicGame);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/games/{city}", method = RequestMethod.GET)
    public ResponseEntity<List<PublicGame>> findGamesByCity(@PathVariable String city, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(gameService.findGamesByCity(city), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.POST)
    public ResponseEntity<Void> joinGame(@PathVariable int gameId, @RequestBody Team team, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            gameService.joinGame(gameId, team);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<PublicGame>> getGamesByTeam(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(gameService.getGamesByTeam(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> leaveGame(@PathVariable int gameId, @RequestBody Team team, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            gameService.leaveGame(gameId, team);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean authenticate(String token, Long id) {
        return gameService.authenticate(token, id);
    }

}
