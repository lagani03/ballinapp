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

@RestController
public class GameController {

    @Autowired
    private final GameService gameService = GameService.getInstance();

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public void createGame(@RequestBody PublicGame publicGame, @RequestHeader(value = "Authorization") String token,
    						@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		gameService.createGame(publicGame);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }

    @RequestMapping(value = "/games/{city}", method = RequestMethod.GET)
    public List<PublicGame> findGamesByCity(@PathVariable String city, @RequestHeader(value = "Authorization") String token,
    											@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		return gameService.findGamesByCity(city);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.POST)
    public void joinGame(@PathVariable int gameId, @RequestBody Team team, @RequestHeader(value = "Authorization") String token,
    						@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		gameService.joinGame(gameId, team);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public List<PublicGame> getGamesByTeam(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
    										@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		return gameService.getGamesByTeam(id);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }

    @RequestMapping(value = "/games/{gameId}", method = RequestMethod.DELETE)
    public void leaveGame(@PathVariable int gameId, @RequestBody Team team, @RequestHeader(value = "Authorization") String token,
    						@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		gameService.leaveGame(gameId, team);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }
    
    public boolean authenticate(String token, Long id) {
		return gameService.authenticate(token, id);
	}

}
