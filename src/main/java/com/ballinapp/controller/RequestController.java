package com.ballinapp.controller;

import com.ballinapp.data.info.NewRequest;
import java.util.List;

import com.ballinapp.data.info.RequestInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class RequestController {

    private final RequestService requestService = RequestService.getInstance();

    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    public ResponseEntity<Void> sendRequest(@RequestBody RequestInfo request) {
        requestService.sendRequest(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.GET)
    public ResponseEntity<RequestInfo> getRequestById(@PathVariable int id) {
        return new ResponseEntity<>(requestService.getRequestById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/new/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<NewRequest>> getRequests(@PathVariable int id) {
        return new ResponseEntity<>(requestService.getRequests(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/all/{teamId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllRequests(@PathVariable int teamId) {
        requestService.deleteAllRequests(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/{requestId}/{response}", method = RequestMethod.PUT)
    public ResponseEntity<Void> requestResponse(@PathVariable int requestId, @PathVariable boolean response) {
        requestService.requestResponse(requestId, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/sent/{teamId}", method = RequestMethod.GET)
    public ResponseEntity<List<NewRequest>> getSentRequests(@PathVariable int teamId) {
        return new ResponseEntity<>(requestService.getSentRequests(teamId), HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRequest(@PathVariable int requestId) {
        requestService.deleteRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> removeFromMyRequests(@PathVariable int requestId) {
        requestService.removeFromMyRequests(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
