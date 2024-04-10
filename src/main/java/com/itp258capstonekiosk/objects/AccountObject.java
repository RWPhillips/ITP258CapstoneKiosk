package com.itp258capstonekiosk.objects;

public class AccountObject {

	private int id;
	private int accountId;
	private String username;
	private String password;

	public AccountObject(String username, String password, int accountId) {
		this.username = username;
		this.password = password;
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public int getAccountId() {
		return accountId;
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
