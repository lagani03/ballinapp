package com.ballinapp.data.info;

/**
 * User: dusan <br/> Date: 3/24/18
 */
public class RefreshInfo {

    private String refresh;
    private int teamId;

    public RefreshInfo(String refresh, int teamId) {
        this.refresh = refresh;
        this.teamId = teamId;
    }

    public RefreshInfo() {
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
