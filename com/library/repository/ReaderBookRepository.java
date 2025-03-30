package com.library.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public final class ReaderBookRepository {
	private static final String filePath = "C:\\Users\\nayho\\IdeaProjects\\library-management-system\\src\\reader_books.txt";

	public static List<String> getAllBooks() {
		try {
			List<String> books = Files.readAllLines(Paths.get(filePath));
			return books;
		} catch (IOException e) {
			throw new RuntimeException("Error at reading file: ", e);
		}
	}

	public static void saveBook(String title) {
		try {
			Files.write(Paths.get(filePath), (title + "\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("Error at writing file: ", e);
		}
	}

	public static void removeBook(String title) {
		List<String> books = getAllBooks();
		List<String> booksAfterRemoved = new ArrayList<>();
		for (String book : books) {
			if (book.equals(title)) {
				continue;
			}
			booksAfterRemoved.add(book);
		}
		try {
			Files.write(Paths.get(filePath), booksAfterRemoved);
		} catch (IOException e) {
			throw new RuntimeException("Error at writing file: ", e);
		}
	}
}
