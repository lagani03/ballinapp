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

@RestController
public class RequestController {

    @Autowired
    private final RequestService requestService = RequestService.getInstance();

    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    public void sendRequest(@RequestBody Request request, @RequestHeader(value = "Authorization") String token,
    							@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		requestService.sendRequest(request);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.GET)
    public Request getRequestById(@PathVariable int id, @RequestHeader(value = "Authorization") String token,
    								@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		return requestService.getRequestById(id);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }

    @RequestMapping(value = "/requests/new/{id}", method = RequestMethod.GET)
    public List<NewRequest> getRequests(@PathVariable Long id, @RequestHeader(value = "Authorization") String token,
    										@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		return requestService.getRequests(id);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }

    @RequestMapping(value = "/requests/all/{teamId}", method = RequestMethod.DELETE)
    public void deleteAllRequests(@PathVariable Long teamId, @RequestHeader(value = "Authorization") String token,
    								@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		requestService.deleteAllRequests(teamId);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }
    
    @RequestMapping(value = "/requests/{requestId}/{response}", method = RequestMethod.PUT)
    public void requestResponse(@PathVariable int requestId, @PathVariable boolean response, @RequestHeader(value = "Authorization") String token,
    								@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		requestService.requestResponse(requestId, response);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }
    
    @RequestMapping(value = "/requests/sent/{teamId}", method = RequestMethod.GET)
    public List<NewRequest> getSentRequests(@PathVariable Long teamId, @RequestHeader(value = "Authorization") String token,
    											@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		return requestService.getSentRequests(teamId);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }
    
    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.DELETE)
    public void deleteRequest(@PathVariable int requestId, @RequestHeader(value = "Authorization") String token,
    							@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		requestService.deleteRequest(requestId);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }
    
    @RequestMapping(value = "/requests/{requestId}", method = RequestMethod.PUT)
    public void removeFromMyRequests(@PathVariable int requestId, @RequestHeader(value = "Authorization") String token,
    									@RequestHeader(value = "ID-Check") Long idCheck) throws AuthenticationException {
    	if(authenticate(token, idCheck)) {
    		requestService.removeFromMyRequests(requestId);
    	} else {
    		throw new AuthenticationException("Credentials not approved!");
    	}
    }
    
    public boolean authenticate(String token, Long id) {
		return requestService.authenticate(token, id);
	}
}
