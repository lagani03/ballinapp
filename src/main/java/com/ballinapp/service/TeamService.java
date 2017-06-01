package com.ballinapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.TeamDao;
import com.ballinapp.data.Player;
import com.ballinapp.data.Team;
import com.ballinapp.exceptions.InvalidDataException;

@Service
public class TeamService {
    
    @Autowired
    private static TeamDao teamDao;
    
    private static final TeamService instance = new TeamService();
    
    private TeamService() {}
    
    public static TeamService getInstance() {
        teamDao = TeamDao.getInstance();
        return instance;
    }

    public Team getTeamById(Long id) {
        teamDao.openCurrentSession();
        Team team = teamDao.getTeamById(id);
        teamDao.closeCurrentSession();
        return team;
    }

    public List<Team> getAllTeams() {
        teamDao.openCurrentSession();
        List<Team> teams = teamDao.getAllTeams();
        teamDao.closeCurrentSession();
        return teams;
    }

    public void addTeam(Team team) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.addTeam(team);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public void updateTeam(Team team, Long id) {
        try {
            if(validateUpdateTeam(team)) {
                teamDao.openCurrentSessionwithTransaction();
                teamDao.updateTeam(team, id);
                teamDao.closeCurrentSessionwithTransaction();
            } else {
                throw new InvalidDataException("Invalid data entered!");
            }
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public List<Player> getAllPlayersByTeam(Long id) {
        teamDao.openCurrentSession();
        List<Player> players = teamDao.getAllPlayersByTeam(id);
        teamDao.closeCurrentSession();
        return players;
    }

    public Player getPlayerById(int id) {
        teamDao.openCurrentSession();
        Player player = teamDao.getPlayerById(id);
        teamDao.closeCurrentSession();
        return player;
    }

    public void addPlayer(Player player, Long id) {
        try {
            if(validatePlayer(player)) {
                teamDao.openCurrentSessionwithTransaction();
                teamDao.addPlayer(player, id);
                teamDao.closeCurrentSessionwithTransaction();
            } else {
                throw new InvalidDataException("Invalid data entered!");
            }
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public void deletePlayer(int id) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.deletePlayer(id);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public List<Team> getTeamsByCity(String cityName) {
        teamDao.openCurrentSession();
        List<Team> teams = teamDao.getTeamsByCity(cityName);
        teamDao.closeCurrentSession();
        return teams;
    }

    public List<Team> getTeamByName(String name) {
        teamDao.openCurrentSession();
        List<Team> teams = teamDao.getTeamByName(name);
        teamDao.closeCurrentSession();
        return teams;
    }

    public void updateTeamAvailability(Long teamId) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.updateTeamAvailability(teamId);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public void updateAppearance(Long teamId, String value) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.updateAppearance(teamId, value);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public boolean checkAcount(Long id) {
        teamDao.openCurrentSession();
        boolean check = teamDao.checkAccount(id);
        teamDao.closeCurrentSession();
        return check;
    }
    
    private boolean validateUpdateTeam(Team team) {
        boolean teamName = false;
        boolean teamState = false;
        boolean teamCity = false;
        boolean teamEmail = false;

        if (team.getName().length() < 25 && !team.getName().isEmpty()) {
            teamName = true;
        }

        if (team.getState().length() < 25 && !team.getState().isEmpty()) {
            int character = 0;
            for (int i = 0; i < team.getState().length(); i++) {
                if (Character.isDigit(team.getState().charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                teamState = true;
            }
        }

        if (team.getCity().length() < 20 && !team.getCity().isEmpty()) {
            int character = 0;
            for (int i = 0; i < team.getCity().length(); i++) {
                if (Character.isDigit(team.getCity().charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                teamCity = true;
            }
        }

        if (!team.getEmail().isEmpty()) {
            int at = 0;
            int dot = 0;
            for (int i = 0; i < team.getEmail().length(); i++) {
                if (team.getEmail().charAt(i) == '@') {
                    at++;
                } else if (team.getEmail().charAt(i) == '.') {
                    dot++;
                }
            }
            if (at > 0 && dot > 0) {
                teamEmail = true;
            }
        }

        return teamName && teamState && teamCity && teamEmail;
    }
    
    private boolean validatePlayer(Player player) {
        boolean nickname = false;
        boolean birthyear = false;
        
        if(!player.getNickname().isEmpty() && player.getNickname().length() < 20) {
            nickname = true;
        }
        
        String byStr = String.valueOf(player.getBirthyear());
        
        if(!byStr.isEmpty() && byStr.length() == 4) {
            birthyear = true;
        }
        
        return nickname && birthyear;
    }
}
