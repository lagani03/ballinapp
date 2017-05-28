package com.ballinapp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "public_game")
public class PublicGame {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "public_game_id")
	private int id;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "contact")
	private String contact;
	
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
	
        @JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "public_game_team", joinColumns=@JoinColumn(name = "public_game_id"),
				inverseJoinColumns=@JoinColumn(name = "team_id"))
	private Set<Team> teams = new HashSet<>();
	
	public PublicGame() {}
	
	public PublicGame(String message, String contact, String state, String city, String address, String date,
			String time) {
		this.message = message;
		this.contact = contact;
		this.state = state;
		this.city = city;
		this.address = address;
		this.date = date;
		this.time = time;
	}
	
	public PublicGame(int id, String message, String contact, String state, String city, String address, String date,
			String time) {
		this.id = id;
		this.message = message;
		this.contact = contact;
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

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	
}
