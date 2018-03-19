package com.ballinapp.dao;

import java.util.ArrayList;
import java.util.List;

import com.ballinapp.dao.base_manager.Manager;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ballinapp.data.model.PublicGame;
import com.ballinapp.data.model.Team;

@Repository
public class GameDao extends Manager {
    
    private static final GameDao instance = new GameDao();
    
    private GameDao() {}
    
    public static GameDao getInstance() {
        return instance;
    }

    private TeamDao teamDao = TeamDao.getInstance();

    public void createGame(PublicGame publicGame) {
        getCurrentSession().save(publicGame);
    }

    public List<PublicGame> findGamesByCity(String city) {
        String hql = "from PublicGame where lower(city) like :city";
        Query query = getCurrentSession().createQuery(hql);
        String lower = city.toLowerCase();
        query.setParameter("city", "%" + lower + "%");
        return query.list();
    }

    public void joinGame(int gameId, int teamId) {
        String sql = "INSERT INTO public_game_team(public_game_id, team_id) VALUES (:gameId, :teamId);";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("gameId", gameId);
        query.setParameter("teamId", teamId);
        query.executeUpdate();
    }

    public List<PublicGame> getGamesByTeam(int id) {
        List<PublicGame> games = new ArrayList<>();

        String sql = "SELECT public_game.id, public_game.message, public_game.contact, " +
                "public_game.state, public_game.city, public_game.address, public_game.date, public_game.time "
                + "FROM public_game_team INNER JOIN public_game "
                + "ON public_game_team.public_game_id = public_game.id WHERE team_id = ?;";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        @SuppressWarnings("unchecked")
		List<Object[]> rows = query.setInteger(0, id).list();
        rows.forEach((row) -> {
            games.add(new PublicGame(Integer.parseInt(row[0].toString()), row[1].toString(), row[2].toString(),
                    row[3].toString(), row[4].toString(), row[5].toString(), row[6].toString(), row[7].toString()));
        });

        return games;
    }

    public void leaveGame(int gameId, int teamId) {
        String queryString = "DELETE FROM public_game_team WHERE public_game_id = ? and team_id = ?";
        SQLQuery query = getCurrentSession().createSQLQuery(queryString);
        query.setParameter(0, gameId);
        query.setParameter(1, teamId);
        query.executeUpdate();
    }
}
