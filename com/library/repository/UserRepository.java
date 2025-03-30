package com.library.repository;

import com.library.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public final class UserRepository {
	private static final String filePath = "C:\\Users\\nayho\\IdeaProjects\\library-management-system\\src\\users.txt";

	public static List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			List<String> usersData = Files.readAllLines(Paths.get(filePath));
			for (String userData : usersData) {
				String[] userInfo = userData.split(",");
				User user = new User(userInfo[0], userInfo[1], userInfo[2]);
				users.add(user);
			}
			return users;
		} catch (IOException e) {
			throw new RuntimeException("Error at reading file: ", e);
		}
	}

	private static boolean doesUserExist(String username) {
		List<User> users = getAllUsers();
		return users.stream().anyMatch(user -> user.getUsername().equals(username));
	}

	public static void addNewUser(User newUser) {
		String rolesRegex = "Admin|Librarian|Reader";
		if (!newUser.getUserRole().matches(rolesRegex)) {
			throw new IllegalArgumentException("Role must be \"Admin\", \"Librarian\" or \"Reader\"!");
		}
		if (newUser.getUsername().isBlank() || newUser.getPassword().isBlank()) {
			throw new IllegalArgumentException("Username and password must not be empty or blank!");
		}
		if (doesUserExist(newUser.getUsername())) {
			throw new IllegalArgumentException("Username has already existed!");
		}
		try {
			Files.write(Paths.get(filePath), (newUser + "\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("Error at writing file: ", e);
		}
	}

	public static void removeUser(String username) {
		if (!doesUserExist(username)) {
			throw new IllegalArgumentException("Username doesn't exist!");
		}
		List<User> users = getAllUsers();
		List<String> usersDataAfterRemoval = new ArrayList<>();
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				continue;
			}
			usersDataAfterRemoval.add(user.toString());
		}
		try {
			Files.write(Paths.get(filePath), usersDataAfterRemoval);
		} catch (IOException e) {
			throw new RuntimeException("Error at writing file: ", e);
		}
	}
}
