package com.ballinapp.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "public_game")
public class PublicGame extends GameRequestMain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
    @JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "public_game_team", joinColumns=@JoinColumn(name = "public_game_id"),
			inverseJoinColumns=@JoinColumn(name = "id"))
	private Set<Team> teams = new HashSet<>();
	
	public PublicGame() {}
	
	public PublicGame(String message, String contact, String state, String city, String address, String date,
			String time) {
		super(message, contact, state, city, address, date, time);
	}
	
	public PublicGame(int id, String message, String contact, String state, String city, String address, String date,
			String time) {
		super(message, contact, state, city, address, date, time);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	
}
