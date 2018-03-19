package com.ballinapp.data.info;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: dusan <br/> Date: 3/11/18
 */
public class TeamInfo {

    private int id;
    private String name;
    private String state;
    private int appearancePlus;
    private int appearanceMinus;
    private Date createdAt;
    private boolean open;
    private boolean active;
    private String email;
    private String city;
    private List<PlayerInfo> players = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAppearancePlus() {
        return appearancePlus;
    }

    public void setAppearancePlus(int appearancePlus) {
        this.appearancePlus = appearancePlus;
    }

    public int getAppearanceMinus() {
        return appearanceMinus;
    }

    public void setAppearanceMinus(int appearanceMinus) {
        this.appearanceMinus = appearanceMinus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<PlayerInfo> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerInfo> players) {
        this.players = players;
    }
}
