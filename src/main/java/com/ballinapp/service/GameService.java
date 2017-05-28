package com.ballinapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.GameDao;
import com.ballinapp.data.PublicGame;
import com.ballinapp.data.Team;

@Service
public class GameService {

    @Autowired
    private static GameDao gameDao;
    
    private static final GameService instance = new GameService();
    
    private GameService() {}
    
    public static GameService getInstance() {
        gameDao = GameDao.getInstance();
        return instance;
    }

    public void createGame(PublicGame publicGame) {
        try {
            gameDao.openCurrentSessionwithTransaction();
            gameDao.createGame(publicGame);
            gameDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            gameDao.getCurrentTransaction().rollback();
        } finally {
            if(gameDao.getCurrentSession().isConnected()) {
                gameDao.closeCurrentSession();
            }
        }
    }

    public List<PublicGame> getAllGames() {
        gameDao.openCurrentSession();
        List<PublicGame> games = gameDao.getAllGames();
        gameDao.closeCurrentSession();
        return games;
    }

    public List<PublicGame> findGamesByCity(String city) {
        gameDao.openCurrentSession();
        List<PublicGame> games = gameDao.findGamesByCity(city);
        gameDao.closeCurrentSession();
        return games;
    }

    public void joinGame(int gameId, Team team) {
        try {
            gameDao.openCurrentSessionwithTransaction();
            gameDao.joinGame(gameId, team);
            gameDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            gameDao.getCurrentTransaction().rollback();
        } finally {
            if(gameDao.getCurrentSession().isConnected()) {
                gameDao.closeCurrentSession();
            }
        }
    }

    public List<PublicGame> getGamesByTeam(Long id) {
        gameDao.openCurrentSession();
        List<PublicGame> games = gameDao.getGamesByTeam(id);
        gameDao.closeCurrentSession();
        return games;
    }

    public void leaveGame(int gameId, Team team) {
        try {
            gameDao.openCurrentSessionwithTransaction();
            gameDao.leaveGame(gameId, team);
            gameDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            gameDao.getCurrentTransaction().rollback();
        } finally {
            if(gameDao.getCurrentSession().isConnected()) {
                gameDao.closeCurrentSession();
            }
        }
    }
}
