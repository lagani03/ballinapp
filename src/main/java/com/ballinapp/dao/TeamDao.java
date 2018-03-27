package com.ballinapp.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ballinapp.dao.base_manager.Manager;
import com.ballinapp.data.info.LoginDataInfo;
import com.ballinapp.data.model.LoginData;
import com.ballinapp.enum_values.AppearanceUpdateEnum;
import com.ballinapp.enum_values.SecretGeneratorEnum;
import com.ballinapp.util.UtilMethods;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ballinapp.data.model.Team;

@Repository
public class TeamDao extends Manager {

    private static final TeamDao instance = new TeamDao();
    
    private TeamDao() {}
    
    public static TeamDao getInstance() {
        return instance;
    }

    public LoginDataInfo addTeam(Team team) {
        team.setPassword(UtilMethods.hashPassword(team.getPassword()));
        getCurrentSession().save(team);
        int teamId = getLastAddedId();
        addLoginData(teamId);
        return getLastActiveLoginData(teamId);
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

    public int login(String name, String pass) {
        String queryString = "from Team where lower(name) = :name and password = :pass";
        Query query = getCurrentSession().createQuery(queryString);
        query.setParameter("name", name.toLowerCase());
        query.setParameter("pass", UtilMethods.hashPassword(pass));
        Team team = (Team) query.uniqueResult();

        if(team == null) {
            return 0;
        }

        return team.getId();
    }

    private int getLastAddedId() {
        String query = "SELECT max(id) FROM team";
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(query);
        return (int) sqlQuery.uniqueResult();
    }

    public void addLoginData(int teamId) {
        invalidatePreviousLoginData(teamId);
        String secret = UtilMethods.generateRandomSecret(SecretGeneratorEnum.SECRET);
        String refresh = UtilMethods.generateRandomSecret(SecretGeneratorEnum.REFRESH);
        String sql = "INSERT INTO login_data(secret, team_id, refresh) VALUES(?, ?, ?)";
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
        sqlQuery.setParameter(0, secret);
        sqlQuery.setParameter(1, teamId);
        sqlQuery.setParameter(2, refresh);
        sqlQuery.executeUpdate();
    }

    public void invalidatePreviousLoginData(int teamId) {
        String hql = "DELETE FROM LoginData WHERE team.id = :teamId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("teamId", teamId);
        query.executeUpdate();
    }

    public LoginDataInfo getLastActiveLoginData(int teamId) {
        String hql = "from LoginData where team.id = :teamId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("teamId", teamId);
        LoginData loginData = (LoginData) query.uniqueResult();
        if(loginData == null) {
            return null;
        }
        return new LoginDataInfo(loginData.getSecret(), loginData.getRefresh(),
                loginData.getTeam().getId());
    }

    public int checkRefreshToken(String token, int id) {
        String hql = "from LoginData where refresh = :token and team.id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("token", token);
        query.setParameter("id", id);
        LoginData loginData = (LoginData) query.uniqueResult();
        if(loginData != null) {
            return loginData.getTeam().getId();
        }
        return 0;
    }

    public boolean checkIfLoggedIn(int teamId) {
        String hql = "SELECT count(*) FROM LoginData WHERE team.id = :teamId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("teamId", teamId);
        Long count = (Long) query.uniqueResult();
        return count > 0;
    }

}
