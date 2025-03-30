package com.library.model;

import com.library.repository.BookRepository;
import com.library.repository.ReaderBookRepository;
import com.library.service.BorrowService;

import java.util.List;

public final class Reader extends User {
	public Reader(String username, String password, String role) {
		super(username, password, role);
	}

	public void borrowBook(String title, String username) {
		BorrowService.borrowBook(title, username);
		ReaderBookRepository.saveBook(title);
	}

	public void returnBook(String title, String username) {
		BorrowService.returnBook(title, username);
		ReaderBookRepository.removeBook(title);
	}

	public void viewAvailableBook() {
		List<Book> books = BookRepository.getAllBooks();
		for (Book book : books) {
			if (!book.getAvailability().equals("available")) {
				continue;
			}
			System.out.println(book.getTitle());
		}
	}
}
