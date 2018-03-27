package com.ballinapp.controller;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;

import com.ballinapp.data.info.LoginInfo;
import com.ballinapp.data.info.RefreshInfo;
import com.ballinapp.jwt.JWTInfo;
import com.ballinapp.data.info.TeamInfo;
import com.ballinapp.jwt.JWTUtil;
import com.ballinapp.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: dusan <br/> Date: 3/20/18
 */
@RestController
public class UserController {

    private final TeamService teamService = TeamService.getInstance();

    @RequestMapping(value = "/register/team", method = RequestMethod.POST)
    public ResponseEntity<JWTInfo> register(@RequestBody TeamInfo teamInfo) {
        return new ResponseEntity<>(teamService.addTeam(teamInfo), HttpStatus.OK);
    }

    @RequestMapping(value = "/register/login", method = RequestMethod.POST)
    public ResponseEntity<JWTInfo> login(@RequestBody LoginInfo info) {
        JWTInfo jwtInfo = teamService.login(info.getTeam(), info.getPass());
        if(jwtInfo == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(jwtInfo, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/register/refresh", method = RequestMethod.POST)
    public ResponseEntity<JWTInfo> login(@RequestBody RefreshInfo refreshInfo) {
        JWTInfo jwtInfo = teamService.refreshToken(refreshInfo.getRefresh(), refreshInfo.getTeamId());
        if(jwtInfo == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(jwtInfo, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/register/logout", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> logout(@RequestBody TeamInfo teamInfo) {
        return new ResponseEntity<>(teamService.logout(teamInfo.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/register/check", method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkIfLoggedIn(@RequestBody TeamInfo teamInfo) {
        return new ResponseEntity<>(teamService.checkIfLoggedIn(teamInfo.getId()), HttpStatus.OK);
    }

}
