package com.ballinapp.data;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id")
	private int id;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "created_at")
	private Date created_at;
	
	@Column(name = "picture")
	private Blob picture;
	
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
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