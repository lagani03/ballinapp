package com.ballinapp.mapping;

import java.util.ArrayList;
import java.util.List;

import com.ballinapp.data.info.RequestInfo;
import com.ballinapp.data.model.Request;
import com.ballinapp.enum_values.MappingTypeEnum;

/**
 * User: dusan <br/> Date: 3/13/18
 */
public class RequestMapper implements CustomMapper<Request, RequestInfo> {

    private TeamMapper teamMapper = new TeamMapper();

    @Override
    public RequestInfo mapToInfo(Request request) {
        RequestInfo info = new RequestInfo();
        info.setId(request.getId());
        info.setSentAt(request.getSentAt());
        info.setSenderTeam(request.getSenderTeamId().getId());
        info.setReceiverTeam(request.getReceiverTeamId().getId());
        info.setStatus(request.isStatus());
        info.setMessage(request.getMessage());
        info.setContact(request.getContact());
        info.setState(request.getState());
        info.setCity(request.getCity());
        info.setAddress(request.getAddress());
        info.setDate(request.getDate());
        info.setTime(request.getTime());
        return info;
    }

    @Override
    public Request mapToModel(RequestInfo requestInfo, MappingTypeEnum typeEnum) {
        Request request = new Request();
        request.setSenderTeamId(teamMapper.convertToModel(requestInfo.getSenderTeam()));
        request.setReceiverTeamId(teamMapper.convertToModel(requestInfo.getReceiverTeam()));
        request.setMessage(requestInfo.getMessage());
        request.setContact(requestInfo.getContact());
        request.setState(requestInfo.getState());
        request.setCity(requestInfo.getCity());
        request.setAddress(requestInfo.getAddress());
        request.setDate(requestInfo.getDate());
        request.setTime(requestInfo.getTime());
        return request;
    }

    @Override
    public List<RequestInfo> mapToInfoList(List<Request> requests) {
        List<RequestInfo> infoList = new ArrayList<>();
        for(Request r : requests) {
            infoList.add(mapToInfo(r));
        }
        return infoList;
    }

    @Override
    public List<Request> mapToModelList(List<RequestInfo> requestInfos) {
        List<Request> requests = new ArrayList<>();
        for(RequestInfo info : requestInfos) {
            requests.add(mapToModel(info, MappingTypeEnum.DEFAULT));
        }
        return requests;
    }

}
