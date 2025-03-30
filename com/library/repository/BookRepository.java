package com.library.repository;

import com.library.model.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public final class BookRepository {
	private static final String filePath = "C:\\Users\\nayho\\IdeaProjects\\library-management-system\\src\\books.txt";

	public static List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		try {
			List<String> booksData = Files.readAllLines(Paths.get(filePath));
			for (String bookData : booksData) {
				String[] bookInfo = bookData.split(",", -1);
				books.add(Book.createBook(bookInfo[0], bookInfo[1], bookInfo[2], bookInfo[3], bookInfo[4], bookInfo[5], bookInfo[6], bookInfo[7], bookInfo[8]));
			}
			return books;
		} catch (IOException e) {
			throw new RuntimeException("Error at reading file: ", e);
		}
	}

	public static void saveAllBooks(List<String> books) {
		try {
			Files.write(Paths.get(filePath), books);
		} catch (IOException e) {
			throw new RuntimeException("Error at writing file: ", e);
		}
	}

	public static boolean doesBookExist(String title) {
		List<Book> books = getAllBooks();
		return books.stream().anyMatch(book -> book.getTitle().equals(title));
	}

	public static void addNewBook(Book newBook) {
		if (doesBookExist(newBook.getTitle())) {
			throw new RuntimeException("Book's title has already existed!");
		}
		try {
			Files.write(Paths.get(filePath), (newBook + "\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("Error at writing file: ", e);
		}
	}

	public static void removeBook(String title) {
		if (!doesBookExist(title)) {
			throw new IllegalArgumentException("Book's title doesn't exist!");
		}
		List<Book> books = getAllBooks();
		List<String> booksDataAfterRemoved = new ArrayList<>();
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				continue;
			}
			booksDataAfterRemoved.add(book.toString());
		}
		try {
			Files.write(Paths.get(filePath), booksDataAfterRemoved);
		} catch (IOException e) {
			throw new RuntimeException("Error at writing file: ", e);
		}
	}
}
