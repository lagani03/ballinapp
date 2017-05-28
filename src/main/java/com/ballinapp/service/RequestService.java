package com.ballinapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.RequestDao;
import com.ballinapp.data.NewRequest;
import com.ballinapp.data.Request;

@Service
public class RequestService {

    @Autowired
    private static RequestDao requestDao;
    
    private static final RequestService instance = new RequestService();
    
    private RequestService() {}
    
    public static RequestService getInstance() {
        requestDao = RequestDao.getInstance();
        return instance;
    }

    public void sendRequest(Request request) {
        try {
            requestDao.openCurrentSessionwithTransaction();
            requestDao.sendRequest(request);
            requestDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            requestDao.getCurrentTransaction().rollback();
        } finally {
            if(requestDao.getCurrentSession().isConnected()) {
                requestDao.closeCurrentSession();
            }
        }
    }

    public Request getRequestById(int id) {
        requestDao.openCurrentSession();
        Request request = requestDao.getRequestById(id);
        requestDao.closeCurrentSession();
        return request;
    }

    public List<NewRequest> getRequests(Long id) {
        requestDao.openCurrentSession();
        List<NewRequest> requests = requestDao.getRequests(id);
        requestDao.closeCurrentSession();
        return requests;
    }

    public void deleteAllRequests(Long teamId) {
        try {
            requestDao.openCurrentSessionwithTransaction();
            requestDao.deleteAllRequests(teamId);
            requestDao.closeCurrentSessionwithTransaction();
        } catch(Exception e) {
            requestDao.getCurrentTransaction().rollback();
        } finally {
            if(requestDao.getCurrentSession().isConnected()) {
                requestDao.closeCurrentSession();
            }
        }
    }
}
