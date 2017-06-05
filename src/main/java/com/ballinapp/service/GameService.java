package com.ballinapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.GameDao;
import com.ballinapp.data.PublicGame;
import com.ballinapp.data.Team;
import com.ballinapp.exceptions.InvalidDataException;

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
            if(validateGame(publicGame)) {
                gameDao.openCurrentSessionwithTransaction();
                gameDao.createGame(publicGame);
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
            if(gameDao.getCurrentTransaction().isActive()) {
                gameDao.getCurrentTransaction().rollback();
            }
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
            if(gameDao.getCurrentTransaction().isActive()) {
                gameDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(gameDao.getCurrentSession().isConnected()) {
                gameDao.closeCurrentSession();
            }
        }
    }
    
    public boolean authenticate(String token, Long id) {
    	gameDao.openCurrentSession();
    	boolean auth = gameDao.authenticate(token, id);
    	gameDao.closeCurrentSession();
    	return auth;
    }
    
    private boolean validateGame(PublicGame game) {
        String message = game.getMessage();
        String contact = game.getContact();
        String state = game.getState();
        String city = game.getCity();
        String address = game.getAddress();
        String date = game.getDate();
        String time = game.getTime();
        
        boolean msg = false;
        boolean cnt = false;
        boolean sta = false;
        boolean cit = false;
        boolean add = false;
        boolean dat = false;
        boolean tim = false;
        
        if(!message.isEmpty()) {
            msg = true;
        }
        
        if(!contact.isEmpty() && contact.length() < 35) {
            cnt = true;
        }
        
        if (state.length() < 25 && !state.isEmpty()) {
            int character = 0;
            for (int i = 0; i < state.length(); i++) {
                if (Character.isDigit(state.charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                sta = true;
            }
        }
        
        if (city.length() < 20 && !city.isEmpty()) {
            int character = 0;
            for (int i = 0; i < city.length(); i++) {
                if (Character.isDigit(city.charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                cit = true;
            }
        }
        
        if(!address.isEmpty() && address.length() < 35) {
            add = true;
        }
        
        if(!date.isEmpty() && date.length() < 20) {
            dat = true;
        }
        
        if (!time.isEmpty() && time.length() < 6) {
            int letter = 0;
            for(int i = 0; i < time.length(); i++) {
                if(Character.isLetter(time.charAt(i))) {
                    letter++;
                }
            }
            if(letter == 0) {
                tim = true;
            }
        }
        
        return msg && cnt && sta && cit && add & dat & tim;
    }
}
