package com.ballinapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.TeamDao;
import com.ballinapp.data.Player;
import com.ballinapp.data.Team;

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
            teamDao.getCurrentTransaction().rollback();
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public void updateTeam(Team team, Long id) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.updateTeam(team, id);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            teamDao.getCurrentTransaction().rollback();
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
            teamDao.openCurrentSessionwithTransaction();
            teamDao.addPlayer(player, id);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            teamDao.getCurrentTransaction().rollback();
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
            teamDao.getCurrentTransaction().rollback();
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

    public Team getTeamByName(String name) {
        teamDao.openCurrentSession();
        Team team = teamDao.getTeamByName(name);
        teamDao.closeCurrentSession();
        return team;
    }

    public void updateTeamAvailability(Long teamId) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.updateTeamAvailability(teamId);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            teamDao.getCurrentTransaction().rollback();
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public void updateAppearancePlus(Long teamId) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.updateAppearancePlus(teamId);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            teamDao.getCurrentTransaction().rollback();
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public void updateAppearanceMinus(Long teamId) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.updateAppearanceMinus(teamId);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            teamDao.getCurrentTransaction().rollback();
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
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
}
