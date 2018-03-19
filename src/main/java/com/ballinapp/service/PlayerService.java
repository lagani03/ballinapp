package com.ballinapp.service;

import java.util.List;

import com.ballinapp.dao.PlayerDao;
import com.ballinapp.data.info.PlayerInfo;
import com.ballinapp.data.model.Player;
import com.ballinapp.enum_values.MappingTypeEnum;
import com.ballinapp.exceptions.InvalidDataException;
import com.ballinapp.mapping.PlayerMapper;
import com.ballinapp.util.UtilMethods;

/**
 * User: dusan <br/> Date: 3/14/18
 */
public class PlayerService {

    private static PlayerDao playerDao;

    private static final PlayerService instance = new PlayerService();

    private PlayerService() {}

    public static PlayerService getInstance() {
        playerDao = PlayerDao.getInstance();
        return instance;
    }

    private TeamService teamService = TeamService.getInstance();

    private PlayerMapper playerMapper = new PlayerMapper();

    public List<PlayerInfo> getAllPlayersByTeam(int id) {
        playerDao.openCurrentSession();
        List<PlayerInfo> infoList = playerMapper.mapToInfoList(playerDao.getAllPlayersByTeam(id));
        playerDao.closeCurrentSession();
        return infoList;
    }

    public PlayerInfo getPlayerById(int id) {
        playerDao.openCurrentSession();
        PlayerInfo player = playerMapper.mapToInfo(playerDao.getPlayerById(id));
        playerDao.closeCurrentSession();
        return player;
    }

    public void addPlayer(PlayerInfo player, int teamId) {
        try {
            if(UtilMethods.validatePlayer(player)) {
                playerDao.openCurrentSessionwithTransaction();
                playerDao.addPlayer(playerMapper.mapToModel(player, MappingTypeEnum.DEFAULT), teamId);
                playerDao.closeCurrentSessionwithTransaction();
            } else {
                throw new InvalidDataException("Invalid data entered!");
            }
        } catch (Exception e) {
            if(playerDao.getCurrentTransaction().isActive()) {
                playerDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (playerDao.getCurrentSession().isConnected()) {
                playerDao.closeCurrentSession();
            }
        }
    }

    public void deletePlayer(int id) {
        try {
            playerDao.openCurrentSessionwithTransaction();
            playerDao.deletePlayer(id);
            playerDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            if(playerDao.getCurrentTransaction().isActive()) {
                playerDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (playerDao.getCurrentSession().isConnected()) {
                playerDao.closeCurrentSession();
            }
        }
    }
}
