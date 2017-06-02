package com.ballinapp.controller;

import com.ballinapp.data.NewRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.data.Request;
import com.ballinapp.service.RequestService;

@RestController
public class RequestController {

    @Autowired
    private final RequestService requestService = RequestService.getInstance();

    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    public void sendRequest(@RequestBody Request request) {
        requestService.sendRequest(request);
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.GET)
    public Request getRequestById(@PathVariable int id) {
        return requestService.getRequestById(id);
    }

    @RequestMapping(value = "/requests/new/{id}", method = RequestMethod.GET)
    public List<NewRequest> getRequests(@PathVariable Long id) {
        return requestService.getRequests(id);
    }

    @RequestMapping(value = "/requests/all/{teamId}", method = RequestMethod.DELETE)
    public void deleteAllRequests(@PathVariable Long teamId) {
        requestService.deleteAllRequests(teamId);
    }
    
    @RequestMapping(value = "/requests/{requestId}/{response}", method = RequestMethod.PUT)
    public void requestResponse(@PathVariable int requestId, @PathVariable boolean response) {
        requestService.requestResponse(requestId, response);
    }
    
    @RequestMapping(value = "/requests/sent/{teamId}", method = RequestMethod.GET)
    public List<NewRequest> getSentRequests(@PathVariable Long teamId) {
        return requestService.getSentRequests(teamId);
    }
    
    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.DELETE)
    public void deleteRequest(@PathVariable int requestId) {
        requestService.deleteRequest(requestId);
    }
    
    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.PUT)
    public void removeFromMyRequests(@PathVariable int requestId) {
        requestService.removeFromMyRequests(requestId);
    }
}
