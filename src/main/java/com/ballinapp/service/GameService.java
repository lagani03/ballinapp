package com.ballinapp.service;

import java.util.List;

import com.ballinapp.data.info.PublicGameInfo;
import com.ballinapp.data.info.TeamInfo;
import com.ballinapp.enum_values.MappingTypeEnum;
import com.ballinapp.mapping.PublicGameMapper;
import com.ballinapp.mapping.TeamMapper;
import com.ballinapp.util.UtilMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.GameDao;
import com.ballinapp.data.model.PublicGame;
import com.ballinapp.data.model.Team;
import com.ballinapp.exceptions.InvalidDataException;

@Service
public class GameService {

    private static GameDao gameDao;
    
    private static final GameService instance = new GameService();
    
    private GameService() {}
    
    public static GameService getInstance() {
        gameDao = GameDao.getInstance();
        return instance;
    }

    private PublicGameMapper gameMapper = new PublicGameMapper();

    private TeamMapper teamMapper = new TeamMapper();

    public void createGame(PublicGameInfo publicGame) {
        try {
            if(UtilMethods.validateGameOrRequestData(publicGame)) {
                gameDao.openCurrentSessionwithTransaction();
                gameDao.createGame(gameMapper.mapToModel(publicGame, MappingTypeEnum.DEFAULT));
                gameDao.closeCurrentSessionwithTransaction();
            } else {
                throw new InvalidDataException("Invalid data entered!");
            }
        } catch(Exception e) {
            if(gameDao.getCurrentTransaction().isActive()) {
                gameDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(gameDao.getCurrentSession().isConnected()) {
                gameDao.closeCurrentSession();
            }
        }
    }

    public List<PublicGameInfo> findGamesByCity(String city) {
        gameDao.openCurrentSession();
        List<PublicGame> publicGames = gameDao.findGamesByCity(city);
        List<PublicGameInfo> publicGameInfoList = gameMapper.mapToInfoList(publicGames);
        gameDao.closeCurrentSession();
        return publicGameInfoList;
    }

    public void joinGame(int gameId, TeamInfo team) {
        try {
            gameDao.openCurrentSessionwithTransaction();
            gameDao.joinGame(gameId, team.getId());
            gameDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            if(gameDao.getCurrentTransaction().isActive()) {
                gameDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(gameDao.getCurrentSession().isConnected()) {
                gameDao.closeCurrentSession();
            }
        }
    }

    public List<PublicGameInfo> getGamesByTeam(int id) {
        gameDao.openCurrentSession();
        List<PublicGameInfo> games = gameMapper.mapToInfoList(gameDao.getGamesByTeam(id));
        gameDao.closeCurrentSession();
        return games;
    }

    public void leaveGame(int gameId, TeamInfo team) {
        try {
            gameDao.openCurrentSessionwithTransaction();
            gameDao.leaveGame(gameId, team.getId());
            gameDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            if(gameDao.getCurrentTransaction().isActive()) {
                gameDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(gameDao.getCurrentSession().isConnected()) {
                gameDao.closeCurrentSession();
            }
        }
    }
}
