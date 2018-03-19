package com.ballinapp.data.info;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * User: dusan <br/> Date: 3/13/18
 */
public class RequestInfo {

    private int id;
    private Date sentAt;
    private int senderTeam;
    private int receiverTeam;
    private boolean status;
    private String message;
    private String contact;
    private String state;
    private String city;
    private String address;
    private String date;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public int getSenderTeam() {
        return senderTeam;
    }

    public void setSenderTeam(int senderTeam) {
        this.senderTeam = senderTeam;
    }

    public int getReceiverTeam() {
        return receiverTeam;
    }

    public void setReceiverTeam(int receiverTeam) {
        this.receiverTeam = receiverTeam;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
