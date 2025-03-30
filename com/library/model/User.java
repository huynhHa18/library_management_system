package com.library.model;

public class User {
	private final String username;
	private final String password;
	private final String role;

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public final String getUserRole() {
		return this.role;
	}

	public final String getUsername() {
		return this.username;
	}

	public final String getPassword() {
		return this.password;
	}

	@Override
	public String toString() {
		return getUsername() + "," + getPassword() + "," + getUserRole();
	}
}
