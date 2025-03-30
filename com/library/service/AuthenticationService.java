package com.library.service;

import com.library.model.Admin;
import com.library.model.Librarian;
import com.library.model.Reader;
import com.library.model.User;
import com.library.repository.UserRepository;

import java.util.List;

public final class AuthenticationService {
	public static User login(String username, String password) {
		List<User> users = UserRepository.getAllUsers();
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return switch (user.getUserRole()) {
					case "Admin" -> new Admin(username, password, "Admin");
					case "Librarian" -> new Librarian(username, password, "Librarian");
					case "Reader" -> new Reader(username, password, "Reader");
					default -> throw new SecurityException("Invalid role.");
				};
			}
		}
		throw new SecurityException("Invalid username or password.");
	}
}
