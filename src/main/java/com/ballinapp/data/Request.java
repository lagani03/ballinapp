package com.ballinapp.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private int id;
	
	@Column(name = "sent_at")
	private Date sentAt;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "contact")
	private String contact;
	
        @ManyToOne(optional = false)
	@JoinColumn(name="sender_team_id", referencedColumnName="team_id")
	private Team senderTeamId;
	
        @ManyToOne(optional = false)
	@JoinColumn(name="receiver_team_id", referencedColumnName="team_id")
	private Team receiverTeamId;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "time")
	private String time;
	
	public Request() {}
	
	public Request(String message, String contact, Team senderTeamId, Team receiverTeamId, String state, String city,
			String address, String date, String time) {
		this.message = message;
		this.contact = contact;
		this.senderTeamId = senderTeamId;
		this.receiverTeamId = receiverTeamId;
		this.state = state;
		this.city = city;
		this.address = address;
		this.date = date;
		this.time = time;
	}

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

	public Team getSenderTeamId() {
		return senderTeamId;
	}

	public void setSenderTeamId(Team senderTeamId) {
		this.senderTeamId = senderTeamId;
	}

	public Team getReceiverTeamId() {
		return receiverTeamId;
	}

	public void setReceiverTeamId(Team receiverTeamId) {
		this.receiverTeamId = receiverTeamId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
