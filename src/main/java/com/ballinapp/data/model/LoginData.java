package com.ballinapp.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * User: dusan <br/> Date: 3/23/18
 */
@Entity
@Table(name = "login_data")
public class LoginData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "secret")
    private String secret;

    @ManyToOne
    @JoinColumn(name="team_id", referencedColumnName="id")
    private Team team;

    @Column(name = "refresh")
    private String refresh;

    public LoginData(String secret, Team team, String refresh) {
        this.secret = secret;
        this.team = team;
        this.refresh = refresh;
    }

    public LoginData() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

}
