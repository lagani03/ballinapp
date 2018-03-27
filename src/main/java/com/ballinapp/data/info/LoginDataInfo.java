package com.ballinapp.data.info;

import java.util.Date;

/**
 * User: dusan <br/> Date: 3/23/18
 */
public class LoginDataInfo {

    private String secret;
    private String refresh;
    private int teamId;

    public LoginDataInfo(String secret, String refresh, int teamId) {
        this.secret = secret;
        this.refresh = refresh;
        this.teamId = teamId;
    }

    public LoginDataInfo() {}

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
