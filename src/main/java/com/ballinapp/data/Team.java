package com.ballinapp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@Column(name = "team_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
	private String state;

	@Column(name = "appearance_plus")
	private int appearance_plus;

	@Column(name = "appearance_minus")
	private int appearance_minus;

	@Column(name = "created_at")
	private Date created_at;

	@Column(name = "open")
	private boolean open;

	@Column(name = "active")
	private boolean active;

	@Column(name = "email")
	private String email;

	@Column(name = "city")
	private String city;

	@Column(name = "picture")
	private Blob picture;

	@Column(name = "access_token")
	private String access_token;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "team_id", nullable = false)
	@JsonIgnore
	List<Player> players = new ArrayList<>();

	public Team(String name, String state, boolean open, boolean active, String email, String city) {
		this.name = name;
		this.state = state;
		this.open = open;
		this.active = active;
		this.email = email;
		this.city = city;
	}

	public Team() {
	};

	public Team(Long id, String name, String state, String email, String city) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.email = email;
		this.city = city;
	}

	public Team(String name, String state, String city, int appearance_plus, int appearance_minus, boolean open) {
		this.name = name;
		this.state = state;
		this.appearance_plus = appearance_plus;
		this.appearance_minus = appearance_minus;
		this.open = open;
		this.city = city;
	}

	public Team(Long id, String name, String state, String city, int appearance_plus, int appearance_minus,
			boolean open) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.appearance_plus = appearance_plus;
		this.appearance_minus = appearance_minus;
		this.open = open;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
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

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public int getAppearance_plus() {
		return appearance_plus;
	}

	public void setAppearance_plus(int appearance_plus) {
		this.appearance_plus = appearance_plus;
	}

	public int getAppearance_minus() {
		return appearance_minus;
	}

	public void setAppearance_minus(int appearance_minus) {
		this.appearance_minus = appearance_minus;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
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
		sb.append(appearance_plus);
		sb.append(",");
		sb.append(appearance_minus);
		sb.append(",");
		sb.append(open);
		return sb.toString();
	}

}
