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

@RestController
public class TeamController {

	@Autowired
	private final TeamService teamService = TeamService.getInstance();

	@RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
	public Team getTeam(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
							@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if (authenticate(token, idCheck)) {
			return teamService.getTeamById(id);
		} else {
			throw new AuthenticationException("Credentials not approved! " + token + " " + String.valueOf(idCheck));
		}
	}

	@RequestMapping(value = "/teams", method = RequestMethod.POST)
	public void addTeam(@RequestBody Team team, @RequestHeader(value = "Authorization") String token,
							@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if (authenticate(token, idCheck)) {
			teamService.addTeam(team);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/teams/{id}", method = RequestMethod.PUT)
	public void updateTeam(@RequestBody Team team, @PathVariable Long id, @RequestHeader(value = "Authorization") String token,
							@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if (authenticate(token, idCheck)) {
			teamService.updateTeam(team, id);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/teams/{id}/players", method = RequestMethod.GET)
	public List<Player> getAllPlayersByTeam(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
												@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if (authenticate(token, idCheck)) {
			return teamService.getAllPlayersByTeam(id);
         } else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/teams/{teamId}/players/{id}", method = RequestMethod.GET)
	public Player getPlayerById(@PathVariable int id, @RequestHeader(value = "Authorization") String token,
									@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if(authenticate(token, idCheck)) {
			return teamService.getPlayerById(id);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/teams/{id}/players", method = RequestMethod.POST)
	public void addPlayer(@RequestBody Player player, @PathVariable Long id, @RequestHeader(value = "Authorization") String token,
			@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if(authenticate(token, idCheck)) {
			teamService.addPlayer(player, id);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/teams/{teamId}/players/{id}", method = RequestMethod.DELETE)
	public void deletePlayer(@PathVariable int id, @RequestHeader(value = "Authorization") String token,
								@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if(authenticate(token, idCheck)) {
			teamService.deletePlayer(id);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/cities/{cityName}", method = RequestMethod.GET)
	public List<Team> getTeamsByCity(@PathVariable String cityName, @RequestHeader(value = "Authorization") String token,
										@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if(authenticate(token, idCheck)) {
			return teamService.getTeamsByCity(cityName);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/team/{teamName}", method = RequestMethod.GET)
	public List<Team> getTeamByName(@PathVariable String teamName, @RequestHeader(value = "Authorization") String token,
										@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if(authenticate(token, idCheck)) {
			return teamService.getTeamByName(teamName);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/teams/{teamId}/open", method = RequestMethod.PUT)
	public void updateTeamAvailability(@PathVariable Long teamId, @RequestHeader(value = "Authorization") String token,
										@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if(authenticate(token, idCheck)) {
			teamService.updateTeamAvailability(teamId);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/teams/{teamId}/appearance/{value}", method = RequestMethod.PUT)
	public void updateAppearance(@PathVariable Long teamId, @PathVariable String value, @RequestHeader(value = "Authorization") String token,
									@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if(authenticate(token, idCheck)) {
			teamService.updateAppearance(teamId, value);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	@RequestMapping(value = "/teams/check/{id}", method = RequestMethod.GET)
	public String checkAccount(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
			@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
		if(authenticate(token, idCheck)) {
			boolean check = teamService.checkAcount(id);
			return String.valueOf(check);
		} else {
			throw new AuthenticationException("Credentials not approved!");
		}
	}

	public boolean authenticate(String token, Long id) {
		return teamService.authenticate(token, id);
	}

}
