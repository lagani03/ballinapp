package com.ballinapp.dao;



import java.util.List;

import com.ballinapp.dao.base_manager.Manager;
import com.ballinapp.data.model.Player;
import com.ballinapp.data.model.Team;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * User: dusan <br/> Date: 3/14/18
 */
@Repository
public class PlayerDao extends Manager {

    private static final PlayerDao instance = new PlayerDao();

    private PlayerDao() {}

    public static PlayerDao getInstance() {
        return instance;
    }

    public List<Player> getAllPlayersByTeam(int id) {
        Team team = (Team) getCurrentSession().get(Team.class, id);
        return team.getPlayers();
    }

    public Player getPlayerById(int id) {
        return (Player) getCurrentSession().get(Player.class, id);
    }

    public void addPlayer(Player player, int teamId) {
        Team team = (Team) getCurrentSession().get(Team.class, teamId);
        team.getPlayers().add(player);
        getCurrentSession().save(team);
    }

    public void deletePlayer(int id) {
        Player player = getPlayerById(id);
        getCurrentSession().delete(player);
    }

}
