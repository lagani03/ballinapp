package com.ballinapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ballinapp.data.PublicGame;
import com.ballinapp.data.Team;
import com.ballinapp.util.HibernateUtil;

@Repository
public class GameDao {
    
    private static final GameDao instance = new GameDao();
    
    private GameDao() {
    }
    
    public static GameDao getInstance() {
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

    public void createGame(PublicGame publicGame) {
        getCurrentSession().save(publicGame);
    }

    public List<PublicGame> findGamesByCity(String city) {
        List<PublicGame> games = new ArrayList<>();

        String sql = "SELECT public_game_id, message, contact, state, city, address, date, time FROM public_game WHERE city LIKE ?";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.setString(0, city).list();
        rows.forEach((row) -> {
            games.add(new PublicGame(Integer.parseInt(row[0].toString()), row[1].toString(), row[2].toString(),
                    row[3].toString(), row[4].toString(), row[5].toString(), row[6].toString(), row[7].toString()));
        });

        return games;
    }

    public void joinGame(int gameId, Team team) {
        String sql = "INSERT INTO public_game_team(public_game_id, team_id) VALUES (?, ?);";
        Long teamId = team.getId();
        getCurrentSession().createSQLQuery(sql).setInteger(0, gameId).setLong(1, teamId).executeUpdate();
    }

    public List<PublicGame> getGamesByTeam(Long id) {
        List<PublicGame> games = new ArrayList<>();

        String sql = "SELECT public_game.public_game_id, public_game.message, public_game.contact, public_game.state, public_game.city, public_game.address, public_game.date, public_game.time "
                + "FROM public_game_team INNER JOIN public_game "
                + "ON public_game_team.public_game_id = public_game.public_game_id WHERE team_id = ?;";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        List<Object[]> rows = query.setLong(0, id).list();
        rows.forEach((row) -> {
            games.add(new PublicGame(Integer.parseInt(row[0].toString()), row[1].toString(), row[2].toString(),
                    row[3].toString(), row[4].toString(), row[5].toString(), row[6].toString(), row[7].toString()));
        });

        return games;
    }

    public void leaveGame(int gameId, Team team) {
        Team getTeam = (Team) getCurrentSession().get(Team.class, team.getId());
        PublicGame publicGame = (PublicGame) getCurrentSession().get(PublicGame.class, gameId);
        Team newTeam = null;
        for (Team t : publicGame.getTeams()) {
            if (t.getId() == getTeam.getId()) {
                newTeam = t;
            }
        }
        publicGame.getTeams().remove(newTeam);
        getCurrentSession().save(publicGame);
    }
}
