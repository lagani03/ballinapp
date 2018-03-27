package com.ballinapp.service;

import java.util.Date;
import java.util.List;

import com.ballinapp.data.info.AppearanceUpdateBean;
import com.ballinapp.data.info.LoginDataInfo;
import com.ballinapp.data.info.TeamInfo;
import com.ballinapp.enum_values.MappingTypeEnum;
import com.ballinapp.jwt.JWTInfo;
import com.ballinapp.jwt.JWTUtil;
import com.ballinapp.mapping.TeamMapper;
import com.ballinapp.util.UtilMethods;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.TeamDao;
import com.ballinapp.exceptions.InvalidDataException;

@Service
public class TeamService {

    private static TeamDao teamDao;
    
    private static final TeamService instance = new TeamService();
    
    private TeamService() {}
    
    public static TeamService getInstance() {
        teamDao = TeamDao.getInstance();
        return instance;
    }

    private TeamMapper teamMapper = new TeamMapper();

    public TeamInfo getTeamById(int id) {
        teamDao.openCurrentSessionwithTransaction();
        TeamInfo teamInfo = teamMapper.mapToInfo(teamDao.getTeamById(id));
        teamDao.closeCurrentSessionwithTransaction();
        return teamInfo;
    }

    public JWTInfo addTeam(TeamInfo team) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            setDefaultTeamValues(team);
            LoginDataInfo loginDataInfo = teamDao.addTeam(teamMapper.mapToModel(team, MappingTypeEnum.ADD));
            teamDao.closeCurrentSessionwithTransaction();
            Date expirationDate = DateUtils.addDays(new Date(), 3);
            return new JWTInfo(JWTUtil.createJWT(loginDataInfo.getTeamId(), loginDataInfo.getSecret(), expirationDate),
                    loginDataInfo.getRefresh(), loginDataInfo.getTeamId());
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
        return null;
    }

    public void updateTeam(TeamInfo team, int id) {
        try {
            if(UtilMethods.validateUpdateTeam(team)) {
                teamDao.openCurrentSessionwithTransaction();
                teamDao.updateTeam(teamMapper.mapToModel(team, MappingTypeEnum.UPDATE), id);
                teamDao.closeCurrentSessionwithTransaction();
            } else {
                throw new InvalidDataException("Invalid data entered!");
            }
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public List<TeamInfo> getTeamsByCity(String cityName) {
        teamDao.openCurrentSession();
        List<TeamInfo> infoList = teamMapper.mapToInfoList(teamDao.getTeamsByCity(cityName));
        teamDao.closeCurrentSession();
        return infoList;
    }

    public List<TeamInfo> getTeamByName(String name) {
        teamDao.openCurrentSession();
        List<TeamInfo> infoList = teamMapper.mapToInfoList(teamDao.getTeamByName(name));
        teamDao.closeCurrentSession();
        return infoList;
    }

    public void updateTeamAvailability(int teamId) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.updateTeamAvailability(teamId);
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if (teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    public void updateAppearance(AppearanceUpdateBean bean) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            teamDao.updateAppearance(bean.getId(), bean.getUpdateEnum());
            teamDao.closeCurrentSessionwithTransaction();
        } catch (Exception e) {
            if(teamDao.getCurrentTransaction().isActive()) {
                teamDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(teamDao.getCurrentSession().isConnected()) {
                teamDao.closeCurrentSession();
            }
        }
    }

    private void setDefaultTeamValues(TeamInfo team) {
        team.setOpen(true);
        team.setActive(true);
    }

    public JWTInfo login(String name, String pass) {
        teamDao.openCurrentSessionwithTransaction();
        int teamId = teamDao.login(name, pass);
        LoginDataInfo lastActiveLoginData = new LoginDataInfo();
        if(teamId != 0) {
            teamDao.addLoginData(teamId);
            lastActiveLoginData = teamDao.getLastActiveLoginData(teamId);
        }
        teamDao.closeCurrentSessionwithTransaction();
        if(UtilMethods.isNotEmpty(lastActiveLoginData.getSecret())) {
            Date expirationDate = DateUtils.addDays(new Date(), 3);
            return new JWTInfo(JWTUtil.createJWT(teamId, lastActiveLoginData.getSecret(), expirationDate), lastActiveLoginData.getRefresh(), teamId);
        } else {
            return null;
        }
    }

    public String getActiveSecret(int teamId) {
        teamDao.openCurrentSession();
        String secret = teamDao.getLastActiveLoginData(teamId).getSecret();
        teamDao.closeCurrentSession();
        return secret;
    }

    public JWTInfo refreshToken(String refresh, int id) {
        teamDao.openCurrentSessionwithTransaction();
        int teamId = teamDao.checkRefreshToken(refresh, id);
        LoginDataInfo lastActive = new LoginDataInfo();
        if(teamId > 0) {
            teamDao.addLoginData(teamId);
            lastActive = teamDao.getLastActiveLoginData(teamId);
        }
        teamDao.closeCurrentSessionwithTransaction();
        if(UtilMethods.isNotEmpty(lastActive.getSecret())) {
            Date expirationDate = DateUtils.addDays(new Date(), 3);
            return new JWTInfo(JWTUtil.createJWT(teamId, lastActive.getSecret(), expirationDate), lastActive.getRefresh(), teamId);
        } else {
            return null;
        }
    }

    public boolean logout(int teamId) {
        teamDao.openCurrentSessionwithTransaction();
        teamDao.invalidatePreviousLoginData(teamId);
        teamDao.closeCurrentSessionwithTransaction();
        return true;
    }

    public boolean checkIfLoggedIn(int teamId) {
        teamDao.openCurrentSession();
        boolean loggedIn = teamDao.checkIfLoggedIn(teamId);
        teamDao.closeCurrentSession();
        return loggedIn;
    }

}
