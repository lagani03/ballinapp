package com.ballinapp.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;

import org.joda.time.DateTime;

@Entity
@Table(name = "request")
public class Request extends GameRequestMain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "sent_at")
	private Date sentAt;
	
    @ManyToOne(optional = false)
	@JoinColumn(name="sender_team_id", referencedColumnName="id")
	private Team senderTeamId;
	
    @ManyToOne(optional = false)
	@JoinColumn(name="receiver_team_id", referencedColumnName="id")
	private Team receiverTeamId;
	
	@Column(name = "status")
	private boolean status;
	
	public Request() {}
	
	public Request(String message, String contact, Team senderTeamId, Team receiverTeamId, String state, String city,
			String address, String date, String time) {
		super(message, contact, state, city, address, date, time);
		this.senderTeamId = senderTeamId;
		this.receiverTeamId = receiverTeamId;
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
	
}
