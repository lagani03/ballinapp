package com.ballinapp.mapping;

import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ballinapp.dao.PlayerDao;
import com.ballinapp.data.info.PlayerInfo;
import com.ballinapp.data.info.TeamInfo;
import com.ballinapp.data.model.Player;
import com.ballinapp.data.model.Team;
import com.ballinapp.enum_values.MappingTypeEnum;
import com.ballinapp.service.PlayerService;
import com.ballinapp.service.TeamService;
import org.springframework.util.StringUtils;

/**
 * User: dusan <br/> Date: 3/11/18
 */
public class TeamMapper implements CustomMapper<Team, TeamInfo> {

    private TeamService teamService = TeamService.getInstance();

    private PlayerMapper playerMapper = new PlayerMapper();

    @Override
    public TeamInfo mapToInfo(Team team) {
        TeamInfo teamInfo = new TeamInfo();
        teamInfo.setId(team.getId());
        teamInfo.setName(team.getName());
        teamInfo.setState(team.getState());
        teamInfo.setAppearancePlus(team.getAppearancePlus());
        teamInfo.setAppearanceMinus(team.getAppearanceMinus());
        teamInfo.setCreatedAt(team.getCreatedAt());
        teamInfo.setOpen(team.isOpen());
        teamInfo.setActive(team.isActive());
        teamInfo.setEmail(team.getEmail());
        teamInfo.setCity(team.getCity());
        teamInfo.setPassword("");
        teamInfo.setPlayers(playerMapper.mapToInfoList(team.getPlayers()));
        return teamInfo;
    }

    @Override
    public Team mapToModel(TeamInfo teamInfo, MappingTypeEnum typeEnum) {
        Team team = new Team();
        team.setName(teamInfo.getName());
        team.setState(teamInfo.getState());
        team.setEmail(teamInfo.getEmail());
        team.setCity(teamInfo.getCity());
        if(MappingTypeEnum.ADD.equals(typeEnum)) {
            team.setPassword(teamInfo.getPassword());
            team.setOpen(teamInfo.isOpen());
            team.setActive(teamInfo.isActive());
        }
        return team;
    }

    @Override
    public List<TeamInfo> mapToInfoList(List<Team> teamList) {
        List<TeamInfo> infoList = new ArrayList<>();
        for(Team team : teamList) {
            infoList.add(mapToInfo(team));
        }
        return infoList;
    }

    @Override
    public List<Team> mapToModelList(List<TeamInfo> infoList) {
        List<Team> teamList = new ArrayList<>();
        for(TeamInfo info : infoList) {
            teamList.add(mapToModel(info, MappingTypeEnum.DEFAULT));
        }
        return teamList;
    }

    public Set<TeamInfo> mapToInfoSet(Set<Team> teamList) {
        Set<TeamInfo> infoSet = new HashSet<>();
        for(Team team : teamList) {
            infoSet.add(mapToInfo(team));
        }
        return infoSet;
    }

    public Set<Team> mapToModelSet(Set<TeamInfo> infoList) {
        Set<Team> teamSet = new HashSet<>();
        for(TeamInfo info : infoList) {
            teamSet.add(mapToModel(info, MappingTypeEnum.DEFAULT));
        }
        return teamSet;
    }

    public int convertToId(TeamInfo info) {
        return info.getId();
    }

    public int convertToId(Team team) {
        return team.getId();
    }

    public TeamInfo convertToInfo(int id) {
        return teamService.getTeamById(id);
    }

    public Team convertToModel(int id) {
        return mapToModel(convertToInfo(id), MappingTypeEnum.DEFAULT);
    }

}
