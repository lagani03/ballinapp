package com.ballinapp.mapping;

import java.util.ArrayList;
import java.util.List;

import com.ballinapp.data.info.PlayerInfo;
import com.ballinapp.data.model.Player;
import com.ballinapp.enum_values.AppearanceUpdateEnum;
import com.ballinapp.enum_values.MappingTypeEnum;
import com.ballinapp.service.TeamService;

/**
 * User: dusan <br/> Date: 3/13/18
 */
public class PlayerMapper implements CustomMapper<Player, PlayerInfo> {

    @Override
    public PlayerInfo mapToInfo(Player player) {
        PlayerInfo info = new PlayerInfo();
        info.setId(player.getId());
        info.setNickname(player.getNickname());
        info.setBirthyear(player.getBirthyear());
        info.setContact(player.getContact());
        return info;
    }

    @Override
    public Player mapToModel(PlayerInfo playerInfo, MappingTypeEnum typeEnum) {
        Player player = new Player();
        player.setNickname(playerInfo.getNickname());
        player.setBirthyear(playerInfo.getBirthyear());
        player.setContact(playerInfo.getContact());
        return player;
    }

    @Override
    public List<PlayerInfo> mapToInfoList(List<Player> players) {
        List<PlayerInfo> infoList = new ArrayList<>();
        for(Player player : players) {
            infoList.add(mapToInfo(player));
        }
        return infoList;
    }

    @Override
    public List<Player> mapToModelList(List<PlayerInfo> infoList) {
        List<Player> players = new ArrayList<>();
        for(PlayerInfo info : infoList) {
            players.add(mapToModel(info, MappingTypeEnum.DEFAULT));
        }
        return players;
    }
}
