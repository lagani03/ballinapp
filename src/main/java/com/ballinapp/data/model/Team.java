package com.ballinapp.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
	private String state;

	@Column(name = "appearance_plus")
	private int appearancePlus;

	@Column(name = "appearance_minus")
	private int appearanceMinus;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "open")
	private boolean open;

	@Column(name = "active")
	private boolean active;

	@Column(name = "email")
	private String email;

	@Column(name = "city")
	private String city;

	@Column(name = "password")
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id", nullable = false)
	private List<Player> players = new ArrayList<>();

	public Team(String name, String state, boolean open, boolean active, String email, String city) {
		this.name = name;
		this.state = state;
		this.open = open;
		this.active = active;
		this.email = email;
		this.city = city;
	}

	public Team() {}

	public Team(int id, String name, String state, String email, String city) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.email = email;
		this.city = city;
	}

	public Team(String name, String state, String city, int appearancePlus, int appearanceMinus, boolean open) {
		this.name = name;
		this.state = state;
		this.appearancePlus = appearancePlus;
		this.appearanceMinus = appearanceMinus;
		this.open = open;
		this.city = city;
	}

	public Team(int id, String name, String state, String city, int appearancePlus, int appearanceMinus,
			boolean open) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.appearancePlus = appearancePlus;
		this.appearanceMinus = appearanceMinus;
		this.open = open;
		this.city = city;
	}

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

	public int getAppearancePlus() {
		return appearancePlus;
	}

	public void setAppearancePlus(int appearance_plus) {
		this.appearancePlus = appearance_plus;
	}

	public int getAppearanceMinus() {
		return appearanceMinus;
	}

	public void setAppearanceMinus(int appearance_minus) {
		this.appearanceMinus = appearance_minus;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(",");
		sb.append(name);
		sb.append(",");
		sb.append(state);
		sb.append(",");
		sb.append(city);
		sb.append(",");
		sb.append(appearancePlus);
		sb.append(",");
		sb.append(appearanceMinus);
		sb.append(",");
		sb.append(open);
		return sb.toString();
	}

}
