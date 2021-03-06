package com.ams.presentacion.user;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class UserDto {
	
	@NotNull
	private int id;
	@NotNull
	private String username;
	@NotNull
	private String password;
	
	private Date lastConnection;
	
	private Integer connectionNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getConnectionNumber() {
		return connectionNumber;
	}

	public void setConnectionNumber(int connectionNumber) {
		this.connectionNumber = connectionNumber;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}
	
	
}
