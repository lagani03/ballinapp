package com.ballinapp.controller;

import java.util.List;

import com.ballinapp.data.info.AppearanceUpdateBean;
import com.ballinapp.data.info.PlayerInfo;
import com.ballinapp.data.info.TeamInfo;
import com.ballinapp.service.PlayerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class TeamController {

    private final TeamService teamService = TeamService.getInstance();

    private final PlayerService playerService = PlayerService.getInstance();

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
    public ResponseEntity<TeamInfo> getTeam(@PathVariable int id) {
        return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public void addTeam(@RequestBody TeamInfo team) {
        teamService.addTeam(team);
    } 

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateTeam(@RequestBody TeamInfo team, @PathVariable int id) { 
        teamService.updateTeam(team, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/{id}/players", method = RequestMethod.GET)
    public ResponseEntity<List<PlayerInfo>> getAllPlayersByTeam(@PathVariable int id) { 
        return new ResponseEntity<>(playerService.getAllPlayersByTeam(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/{teamId}/players/{id}", method = RequestMethod.GET)
    public ResponseEntity<PlayerInfo> getPlayerById(@PathVariable int id) { 
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/{teamId}/players", method = RequestMethod.POST)
    public ResponseEntity<Void> addPlayer(@RequestBody PlayerInfo player, @PathVariable int teamId) { 
        playerService.addPlayer(player, teamId);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/teams/{teamId}/players/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) { 
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/cities/{cityName}", method = RequestMethod.GET)
    public ResponseEntity<List<TeamInfo>> getTeamsByCity(@PathVariable String cityName) { 
        return new ResponseEntity<>(teamService.getTeamsByCity(cityName), HttpStatus.OK);
    }

    @RequestMapping(value = "/team/{teamName}", method = RequestMethod.GET)
    public ResponseEntity<List<TeamInfo>> getTeamByName(@PathVariable String teamName) { 
            return new ResponseEntity<>(teamService.getTeamByName(teamName), HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/{teamId}/available", method = RequestMethod.PUT) 
    public ResponseEntity<Void> updateTeamAvailability(@PathVariable int teamId) {
        teamService.updateTeamAvailability(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/teams/appearance", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAppearance(@RequestBody AppearanceUpdateBean bean) { 
        teamService.updateAppearance(bean);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
