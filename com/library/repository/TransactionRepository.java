package com.library.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class TransactionRepository {
	private static final String filePath = "C:\\Users\\nayho\\IdeaProjects\\library-management-system\\src\\transactions.txt";

	public static void saveTransaction(String transaction) {
		try {
			Files.write(Paths.get(filePath), (transaction + "\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("Error at writing fille: ", e);
		}
	}
}
