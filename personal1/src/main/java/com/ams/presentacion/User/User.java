package com.ams.presentacion.User;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="appuser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String username;

	@JsonIgnore
	private String password;
/*
	@Column(nullable = true)
	private Date lastConnection;

	@Column(nullable = true)
	private int connectionNumber;
*/
	protected User() {
	};

	public User(String username, String password, Date lastConnection, int connectionNumber) {
		super();
		this.username = username;
		this.password = password;
		//this.lastConnection = lastConnection;
		//this.connectionNumber = connectionNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwd) {
		this.password = passwd;
	}
/*
	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	public int getConnectionNumber() {
		return connectionNumber;
	}

	public void setConnectionNumber(int connectionNumber) {
		this.connectionNumber = connectionNumber;
	}
	*/
}
