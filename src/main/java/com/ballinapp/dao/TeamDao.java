package com.ballinapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ballinapp.data.Player;
import com.ballinapp.data.Team;
import com.ballinapp.util.HibernateUtil;

@Repository
public class TeamDao {
    private static final TeamDao instance = new TeamDao();
    
    private TeamDao() {
    }
    
    public static TeamDao getInstance() {
        return instance;
    }
    
    private Session currentSession;
    private Transaction currentTransaction;
    
    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public Team getTeamById(Long id) {
        Team team = (Team) getCurrentSession().get(Team.class, id);
        return team;
    }

    public void addTeam(Team team) {
        getCurrentSession().save(team);
    }

    public void updateTeam(Team team, Long id) {
        Team old = getTeamById(id);

        old.setName(team.getName());
        old.setEmail(team.getEmail());
        old.setState(team.getState());
        old.setCity(team.getCity());

        getCurrentSession().save(old);
    }

    public List<Player> getAllPlayersByTeam(Long id) {
        List<Player> players = new ArrayList<>();

        Team workingTeam = (Team) getCurrentSession().get(Team.class, id);

        workingTeam.getPlayers().forEach((p) -> {
            players.add(p);
        });

        return players;
    }

    public Player getPlayerById(int id) {
        return (Player) getCurrentSession().get(Player.class, id);
    }

    public void addPlayer(Player player, Long id) {
        Team team = (Team) getCurrentSession().get(Team.class, id);
        team.getPlayers().add(player);
        getCurrentSession().save(team);
    }

    public void deletePlayer(int id) {
        Player player = getPlayerById(id);
        getCurrentSession().delete(player);
    }

    public List<Team> getTeamsByCity(String cityName) {
        List<Team> teams = new ArrayList<>();

        String sql = "SELECT team_id, name, state, city, appearance_plus, appearance_minus, open FROM team WHERE city LIKE ?";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.setString(0, cityName).list();
        for (Object[] row : rows) {
            teams.add(new Team(Long.parseLong(row[0].toString()), row[1].toString(), row[2].toString(), row[3].toString(),
                    Integer.parseInt(row[4].toString()), Integer.parseInt(row[5].toString()),
                    Boolean.parseBoolean(row[6].toString())));
        }

        return teams;
    }

    public List<Team> getTeamByName(String name) {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT team_id, name, state, city, appearance_plus, appearance_minus, open FROM team WHERE name LIKE ?";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        @SuppressWarnings("unchecked")
		List<Object[]> rows = query.setString(0, name).list();
        for (Object[] row : rows) {
            teams.add(new Team(Long.parseLong(row[0].toString()), row[1].toString(), row[2].toString(), row[3].toString(),
                    Integer.parseInt(row[4].toString()), Integer.parseInt(row[5].toString()),
                    Boolean.parseBoolean(row[6].toString())));
        }

        return teams;
    }

    public void updateTeamAvailability(Long teamId) {
        Team team = (Team) getCurrentSession().get(Team.class, teamId);
        if (team.isOpen()) {
            team.setOpen(false);
        } else if (!team.isOpen()) {
            team.setOpen(true);
        }
        getCurrentSession().save(team);
    }

    public void updateAppearance(Long teamId, String value) {
        Team team = (Team) getCurrentSession().get(Team.class, teamId);
        if("plus".equals(value)) {
            team.setAppearance_plus(team.getAppearance_plus() + 1);
        } else {
            team.setAppearance_minus(team.getAppearance_minus() + 1);
        }
        getCurrentSession().save(team);
    }

    public boolean checkAccount(Long id) {
        String sql = "SELECT * FROM team WHERE team_id = ?";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        int i = 0;

        @SuppressWarnings("unchecked")
		List<Object[]> rows = query.setLong(0, id).list();
        i = rows.stream().map((_item) -> 1).reduce(i, Integer::sum);

        return i != 0;
    }
    
    public boolean authenticate(String token, Long id) {
    	String sql = "SELECT * FROM team WHERE access_token = ? and team_id = ?";
    	SQLQuery query = getCurrentSession().createSQLQuery(sql);
    	int i = 0;
    	@SuppressWarnings("unchecked")
		List<Object[]> rows = query.setString(0, token).setLong(1, id).list();
        for (@SuppressWarnings("unused") Object[] row : rows) {
        	i++;
        }
        
        if(i == 0) {
        	return false;
        }
    	
    	return true;
    }
}
