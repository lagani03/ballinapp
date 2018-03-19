package com.ballinapp.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "player")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "birthyear")
	private int birthyear;
	
	@Column(name = "contact")
	private String contact;
	
	public Player() {}
	
	public Player(String nickname, int birthyear) {
		this.nickname = nickname;
		this.birthyear = birthyear;
	}
	
	public Player(String nickname, int birthyear, String contact) {
		this.nickname = nickname;
		this.birthyear = birthyear;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}