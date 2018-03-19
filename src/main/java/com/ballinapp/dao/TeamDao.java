package com.ballinapp.dao;

import java.util.ArrayList;
import java.util.List;

import com.ballinapp.dao.base_manager.Manager;
import com.ballinapp.enum_values.AppearanceUpdateEnum;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ballinapp.data.model.Team;

@Repository
public class TeamDao extends Manager {

    private static final TeamDao instance = new TeamDao();
    
    private TeamDao() {}
    
    public static TeamDao getInstance() {
        return instance;
    }

    public void addTeam(Team team) {
        getCurrentSession().save(team);
    }

    public Team getTeamById(int id) {
        return (Team) getCurrentSession().get(Team.class, id);
    }

    public void updateTeam(Team team, int id) {
        Team old = getTeamById(id);
        old.setName(team.getName());
        old.setEmail(team.getEmail());
        old.setState(team.getState());
        old.setCity(team.getCity());
        getCurrentSession().save(old);
    }

    public List<Team> getTeamsByCity(String cityName) {
        String queryString = "FROM Team WHERE lower(city) LIKE :cityName";
        Query query = getCurrentSession().createQuery(queryString);
        String lower = cityName.toLowerCase();
        query.setParameter("cityName", lower + "%");
        return query.list();
    }

    public List<Team> getTeamByName(String name) {
        String queryString = "FROM Team WHERE lower(name) LIKE :teamName";
        Query query = getCurrentSession().createQuery(queryString);
        String lower = name.toLowerCase();
        query.setParameter("teamName", lower + "%");
        return query.list();
    }

    public void updateTeamAvailability(int teamId) {
        Team team = (Team) getCurrentSession().get(Team.class, teamId);
        if (team.isOpen()) {
            team.setOpen(false);
        } else if (!team.isOpen()) {
            team.setOpen(true);
        }
        getCurrentSession().save(team);
    }

    public void updateAppearance(int teamId, AppearanceUpdateEnum updateEnum) {
        Team team = (Team) getCurrentSession().get(Team.class, teamId);
        switch (updateEnum) {
            case PLUS:
                team.setAppearancePlus(team.getAppearancePlus() + 1);
                break;
            case MINUS:
                team.setAppearanceMinus(team.getAppearanceMinus() + 1);
                break;
        }
    }

}
