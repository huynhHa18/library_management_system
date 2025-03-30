package com.library.model;

import com.library.repository.UserRepository;

import java.util.List;

public final class Admin extends User {
	public Admin(String username, String password, String role) {
		super(username, password, role);
	}

	public void addNewUser(User newUser) {
		UserRepository.addNewUser(newUser);
	}

	public void removeUser(String username) {
		UserRepository.removeUser(username);
	}

	public void listAllUsers() {
		List<User> users = UserRepository.getAllUsers();
		for (User user : users) {
			System.out.println(user);
		}
	}
}
