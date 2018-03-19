package com.ballinapp.service;

import java.util.List;

import com.ballinapp.data.info.RequestInfo;
import com.ballinapp.enum_values.MappingTypeEnum;
import com.ballinapp.mapping.RequestMapper;
import com.ballinapp.util.UtilMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.RequestDao;
import com.ballinapp.data.info.NewRequest;
import com.ballinapp.data.model.Request;
import com.ballinapp.exceptions.InvalidDataException;

@Service
public class RequestService {

    private static RequestDao requestDao;
    
    private static final RequestService instance = new RequestService();
    
    private RequestService() {}
    
    public static RequestService getInstance() {
        requestDao = RequestDao.getInstance();
        return instance;
    }

    private RequestMapper requestMapper = new RequestMapper();

    public void sendRequest(RequestInfo request) {
        try {
            if(UtilMethods.validateGameOrRequestData(request)) {
                requestDao.openCurrentSessionwithTransaction();
                requestDao.sendRequest(requestMapper.mapToModel(request, MappingTypeEnum.DEFAULT));
                requestDao.closeCurrentSessionwithTransaction();
            } else {
                throw new InvalidDataException("Invalid data entered!");
            }
        } catch(Exception e) {
            if(requestDao.getCurrentTransaction().isActive()) {
                requestDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(requestDao.getCurrentSession().isConnected()) {
                requestDao.closeCurrentSession();
            }
        }
    }

    public RequestInfo getRequestById(int id) {
        requestDao.openCurrentSession();
        RequestInfo request = requestMapper.mapToInfo(requestDao.getRequestById(id));
        requestDao.closeCurrentSession();
        return request;
    }

    public List<NewRequest> getRequests(int id) {
        requestDao.openCurrentSession();
        List<NewRequest> requests = requestDao.getRequests(id);
        requestDao.closeCurrentSession();
        return requests;
    }

    public void deleteAllRequests(int teamId) {
        try {
            requestDao.openCurrentSessionwithTransaction();
            requestDao.deleteAllRequests(teamId);
            requestDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            if(requestDao.getCurrentTransaction().isActive()) {
                requestDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(requestDao.getCurrentSession().isConnected()) {
                requestDao.closeCurrentSession();
            }
        }
    }
    
    public void requestResponse(int requestId, boolean response) {
        try {
            requestDao.openCurrentSessionwithTransaction();
            requestDao.requestResponse(requestId, response);
            requestDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            if(requestDao.getCurrentTransaction().isActive()) {
                requestDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(requestDao.getCurrentSession().isConnected()) {
                requestDao.closeCurrentSession();
            }
        }
    }
    
    public List<NewRequest> getSentRequests(int teamId) {
        requestDao.openCurrentSession();
        List<NewRequest> requests = requestDao.getSentRequests(teamId);
        requestDao.closeCurrentSession();
        return requests;
    }
    
    public void deleteRequest(int requestId) {
        try {
            requestDao.openCurrentSessionwithTransaction();
            requestDao.deleteRequest(requestId);
            requestDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            if(requestDao.getCurrentTransaction().isActive()) {
                requestDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(requestDao.getCurrentSession().isConnected()) {
                requestDao.closeCurrentSession();
            }
        }
    }
    
    public void removeFromMyRequests(int requestId) {
        try {
            requestDao.openCurrentSessionwithTransaction();
            requestDao.removeFromMyRequests(requestId);
            requestDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            if(requestDao.getCurrentTransaction().isActive()) {
                requestDao.getCurrentTransaction().rollback();
            }
        } finally {
            if(requestDao.getCurrentSession().isConnected()) {
                requestDao.closeCurrentSession();
            }
        }
    }

}
