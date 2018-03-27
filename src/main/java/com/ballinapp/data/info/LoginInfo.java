package com.ballinapp.data.info;

/**
 * User: dusan <br/> Date: 3/23/18
 */
public class LoginInfo {

    private String team;
    private String pass;

    public LoginInfo(String team, String pass) {
        this.team = team;
        this.pass = pass;
    }

    public LoginInfo() {}

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
