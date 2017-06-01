package com.ballinapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.data.Player;
import com.ballinapp.data.Team;
import com.ballinapp.service.TeamService;

@RestController
public class TeamController {

    @Autowired
    private final TeamService teamService = TeamService.getInstance();

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
    public Team getTeam(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public void addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.PUT)
    public void updateTeam(@RequestBody Team team, @PathVariable Long id) {
        teamService.updateTeam(team, id);
    }

    @RequestMapping(value = "/teams/{id}/players", method = RequestMethod.GET)
    public List<Player> getAllPlayersByTeam(@PathVariable Long id) {
        return teamService.getAllPlayersByTeam(id);
    }

    @RequestMapping(value = "/teams/{teamId}/players/{id}", method = RequestMethod.GET)
    public Player getPlayerById(@PathVariable int id) {
        return teamService.getPlayerById(id);
    }

    @RequestMapping(value = "/teams/{id}/players", method = RequestMethod.POST)
    public void addPlayer(@RequestBody Player player, @PathVariable Long id) {
        teamService.addPlayer(player, id);
    }

    @RequestMapping(value = "/teams/{teamId}/players/{id}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable int id) {
        teamService.deletePlayer(id);
    }

    @RequestMapping(value = "/cities/{cityName}", method = RequestMethod.GET)
    public List<Team> getTeamsByCity(@PathVariable String cityName) {
        return teamService.getTeamsByCity(cityName);
    }

    @RequestMapping(value = "/team/{teamName}", method = RequestMethod.GET)
    public List<Team> getTeamByName(@PathVariable String teamName) {
        return teamService.getTeamByName(teamName);
    }

    @RequestMapping(value = "/teams/{teamId}/open", method = RequestMethod.PUT)
    public void updateTeamAvailability(@PathVariable Long teamId) {
        teamService.updateTeamAvailability(teamId);
    }

    @RequestMapping(value = "/teams/{teamId}/appearance/{value}", method = RequestMethod.PUT)
    public void updateAppearance(@PathVariable Long teamId, @PathVariable String value) {
        teamService.updateAppearance(teamId, value);
    }

    @RequestMapping(value = "/teams/check/{id}", method = RequestMethod.GET)
    public String checkAccount(@PathVariable Long id) {
        boolean check = teamService.checkAcount(id);
        return String.valueOf(check);
    }

}
