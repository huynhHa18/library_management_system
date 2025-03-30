package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class BorrowService {
	public static void borrowBook(String title, String username) {
		if (!BookRepository.doesBookExist(title)) {
			throw new IllegalArgumentException("Book's title doesn't exist!");
		}
		List<Book> books = BookRepository.getAllBooks();
		List<String> booksDataAfterBorrowed = new ArrayList<>();
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				book.setAvailability("borrowed");
				String dueDate = LocalDateTime.now().plusDays(14).toString();
				book.setDueDate(dueDate);
				String transaction = String.join(",", username, "borrowed", title, dueDate);
				TransactionRepository.saveTransaction(transaction);
			}
			booksDataAfterBorrowed.add(book.toString());
		}
		BookRepository.saveAllBooks(booksDataAfterBorrowed);
	}

	public static void returnBook(String title, String username) {
		if (!BookRepository.doesBookExist(title)) {
			throw new IllegalArgumentException("Book's title doesn't exist!");
		}
		List<Book> books = BookRepository.getAllBooks();
		List<String> booksDataAfterReturned = new ArrayList<>();
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				book.setAvailability("available");
				book.setDueDate("");
				String returnDay = LocalDateTime.now().toString();
				String transaction = String.join(",", username, "returned", title, returnDay);
				TransactionRepository.saveTransaction(transaction);
			}
			booksDataAfterReturned.add(book.toString());
		}
		BookRepository.saveAllBooks(booksDataAfterReturned);
	}
}

