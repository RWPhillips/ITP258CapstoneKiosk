package com.itp258capstonekiosk.objects;

import java.io.Serializable;

public class AccountObject implements Serializable{

	private int id;
	private String username;
	private String password;

	public AccountObject(String username) {
		this.username = username;
	}

	public AccountObject(String username, String password) {
		this.username = username;
		this.password = password;
	}

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

}
