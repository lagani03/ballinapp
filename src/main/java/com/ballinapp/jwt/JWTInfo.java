package com.ballinapp.jwt;

/**
 * User: dusan <br/> Date: 3/20/18
 */
public class JWTInfo {

    public JWTInfo(String accessToken, String refresh, int teamId) {
        this.accessToken = accessToken;
        this.refresh = refresh;
        this.teamId = teamId;
    }

    private String accessToken;

    private String refresh;

    private int teamId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
