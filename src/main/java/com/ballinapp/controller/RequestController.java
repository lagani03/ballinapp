package com.ballinapp.controller;

import com.ballinapp.data.NewRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ballinapp.data.Request;
import com.ballinapp.exceptions.AuthenticationException;
import com.ballinapp.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class RequestController {

    @Autowired
    private final RequestService requestService = RequestService.getInstance();

    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    public ResponseEntity<Void> sendRequest(@RequestBody Request request, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            requestService.sendRequest(request);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.GET)
    public ResponseEntity<Request> getRequestById(@PathVariable int id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(requestService.getRequestById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/requests/new/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<NewRequest>> getRequests(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(requestService.getRequests(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/requests/all/{teamId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllRequests(@PathVariable Long teamId, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            requestService.deleteAllRequests(teamId);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/{requestId}/{response}", method = RequestMethod.PUT)
    public ResponseEntity<Void> requestResponse(@PathVariable int requestId, @PathVariable boolean response, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            requestService.requestResponse(requestId, response);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/sent/{teamId}", method = RequestMethod.GET)
    public ResponseEntity<List<NewRequest>> getSentRequests(@PathVariable Long teamId, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            return new ResponseEntity<>(requestService.getSentRequests(teamId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRequest(@PathVariable int requestId, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            requestService.deleteRequest(requestId);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> removeFromMyRequests(@PathVariable int requestId, @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
        if (authenticate(token, idCheck)) {
            requestService.removeFromMyRequests(requestId);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean authenticate(String token, Long id) {
        return requestService.authenticate(token, id);
    }
}
