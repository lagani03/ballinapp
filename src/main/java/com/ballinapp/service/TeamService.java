package com.ballinapp.service;

import java.util.List;

import com.ballinapp.data.info.AppearanceUpdateBean;
import com.ballinapp.data.info.PlayerInfo;
import com.ballinapp.data.info.TeamInfo;
import com.ballinapp.data.model.Team;
import com.ballinapp.enum_values.AppearanceUpdateEnum;
import com.ballinapp.enum_values.MappingTypeEnum;
import com.ballinapp.mapping.PlayerMapper;
import com.ballinapp.mapping.TeamMapper;
import com.ballinapp.util.UtilMethods;
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

    public void addTeam(TeamInfo team) {
        try {
            teamDao.openCurrentSessionwithTransaction();
            setDefaultTeamValues(team);
            teamDao.addTeam(teamMapper.mapToModel(team, MappingTypeEnum.ADD));
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
}
