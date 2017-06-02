package com.ballinapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ballinapp.dao.RequestDao;
import com.ballinapp.data.NewRequest;
import com.ballinapp.data.Request;
import com.ballinapp.exceptions.InvalidDataException;

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
            if(validateRequest(request)) {
                requestDao.openCurrentSessionwithTransaction();
                requestDao.sendRequest(request);
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
    
    public List<NewRequest> getSentRequests(Long teamId) {
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
    
    private boolean validateRequest(Request request) {
        String message = request.getMessage();
        String contact = request.getContact();
        String state = request.getState();
        String city = request.getCity();
        String address = request.getAddress();
        String date = request.getDate();
        String time = request.getTime();
        
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
