package com.ballinapp.mapping;

import java.util.ArrayList;
import java.util.List;

import com.ballinapp.data.info.PublicGameInfo;
import com.ballinapp.data.model.PublicGame;
import com.ballinapp.enum_values.MappingTypeEnum;
import org.springframework.util.StringUtils;

/**
 * User: dusan <br/> Date: 3/13/18
 */
public class PublicGameMapper implements CustomMapper<PublicGame, PublicGameInfo>{

    private TeamMapper teamMapper = new TeamMapper();

    @Override
    public PublicGameInfo mapToInfo(PublicGame publicGame) {
        PublicGameInfo info = new PublicGameInfo();
        info.setId(publicGame.getId());
        info.setAddress(publicGame.getAddress());
        info.setCity(publicGame.getCity());
        info.setContact(publicGame.getContact());
        info.setDate(publicGame.getDate());
        info.setMessage(publicGame.getMessage());
        info.setState(publicGame.getState());
        info.setTime(publicGame.getTime());
        return info;
    }

    @Override
    public PublicGame mapToModel(PublicGameInfo publicGameInfo, MappingTypeEnum typeEnum) {
        PublicGame publicGame = new PublicGame();
        publicGame.setId(publicGameInfo.getId());
        publicGame.setAddress(publicGameInfo.getAddress());
        publicGame.setCity(publicGameInfo.getCity());
        publicGame.setContact(publicGameInfo.getContact());
        publicGame.setDate(publicGameInfo.getDate());
        publicGame.setMessage(publicGameInfo.getMessage());
        publicGame.setState(publicGameInfo.getState());
        publicGame.setTime(publicGameInfo.getTime());
        return publicGame;
    }

    @Override
    public List<PublicGameInfo> mapToInfoList(List<PublicGame> publicGames) {
        List<PublicGameInfo> infoList = new ArrayList<>();
        for(PublicGame game : publicGames) {
            infoList.add(mapToInfo(game));
        }
        return infoList;
    }

    @Override
    public List<PublicGame> mapToModelList(List<PublicGameInfo> publicGameInfos) {
        List<PublicGame> publicGames = new ArrayList<>();
        for(PublicGameInfo info : publicGameInfos) {
            publicGames.add(mapToModel(info, MappingTypeEnum.DEFAULT));
        }
        return publicGames;
    }
}
