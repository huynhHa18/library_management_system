package com.library.model;

import com.library.repository.BookRepository;

import java.util.List;

public final class Librarian extends User {
	public Librarian(String username, String password, String role) {
		super(username, password, role);
	}

	public void addNewBook(Book newBook) {
		BookRepository.addNewBook(newBook);
	}

	public void removeBook(String title) {
		BookRepository.removeBook(title);
	}

	public void listAllBooks() {
		List<Book> books = BookRepository.getAllBooks();
		for (Book book : books) {
			if (!book.getAvailability().equals("available")) {
				continue;
			}
			System.out.println(book);
		}
	}
}
