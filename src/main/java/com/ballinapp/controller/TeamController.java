package com.ballinapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.data.Player;
import com.ballinapp.data.Team;
import com.ballinapp.exceptions.AuthenticationException;
import com.ballinapp.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class TeamController {

    @Autowired
    private final TeamService teamService = TeamService.getInstance();

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
    public ResponseEntity<Team> getTeam(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public void addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateTeam(@RequestBody Team team, @PathVariable Long id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            teamService.updateTeam(team, id);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/{id}/players", method = RequestMethod.GET)
    public ResponseEntity<List<Player>> getAllPlayersByTeam(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(teamService.getAllPlayersByTeam(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/teams/{teamId}/players/{id}", method = RequestMethod.GET)
    public ResponseEntity<Player> getPlayerById(@PathVariable int id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(teamService.getPlayerById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/teams/{id}/players", method = RequestMethod.POST)
    public ResponseEntity<Void> addPlayer(@RequestBody Player player, @PathVariable Long id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            teamService.addPlayer(player, id);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/{teamId}/players/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePlayer(@PathVariable int id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            teamService.deletePlayer(id);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cities/{cityName}", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> getTeamsByCity(@PathVariable String cityName, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(teamService.getTeamsByCity(cityName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/team/{teamName}", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> getTeamByName(@PathVariable String teamName, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(teamService.getTeamByName(teamName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/teams/{teamId}/open", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateTeamAvailability(@PathVariable Long teamId, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            teamService.updateTeamAvailability(teamId);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/{teamId}/appearance/{value}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAppearance(@PathVariable Long teamId, @PathVariable String value, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            teamService.updateAppearance(teamId, value);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/check/{id}", method = RequestMethod.GET)
    public String checkAccount(@PathVariable Long id) {
        boolean check = teamService.checkAcount(id);
        return String.valueOf(check);
    }

    public boolean authenticate(String token, Long id) {
        return teamService.authenticate(token, id);
    }
    
}
