package com.library.model;

public final class Book {
	private final String type;
	private final String title;
	private final String author;
	private final String genre;
	private final String isbn;
	private final String pages;
	private final String fileFormat;
	private String dueDate;
	private String availability;

	private Book(String type, String title, String author, String genre, String isbn, String availability, String dueDate, String pages, String fileFormat) {
		this.type = type;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.isbn = isbn;
		this.availability = availability;
		this.dueDate = dueDate;
		this.pages = pages;
		this.fileFormat = fileFormat;
	}

	public static Book createBook(String type, String title, String author, String genre, String isbn, String availability, String dueDate, String pages, String fileFormat) {
		if (!type.matches("ebook|printed book")) {
			throw new RuntimeException("Book's type should be \"Ebook\" or \"Printed Book\"");
		}
		if (title.length() > 255 || title.isBlank()) {
			throw new RuntimeException("Book's title should be between 1 and 255 characters long!");
		}
		if (author.length() > 30 || author.isBlank()) {
			throw new RuntimeException("Book's author should be between 1 and 30 characters long!");
		}
		String genresRegex = "action|adventure|animation|comedy|crime|documentary";
		if (!genre.matches(genresRegex)) {
			throw new RuntimeException("Book's genre should be one of these: \"Action\", \"Adventure\", \"Animation\", \"Comedy\", \"Crime\", \"Documentary\"");
		}
		if (isbn.length() > 13) {
			throw new RuntimeException("Book's isbn code should be 13 characters long!");
		}
		String availabilityRegex = "borrowed|available|not for loan";
		if (!availability.matches(availabilityRegex)) {
			throw new RuntimeException("Book's availability should be one of these: \"Borrowed\", \"Available\", \"Not For Loan\"!");
		}
		if (type.equals("printed book")) {
			String pagesRegex = "[1-9]\\d*";
			if (!pages.matches(pagesRegex)) {
				throw new RuntimeException("Book's pages should be numbers and start from 1!");
			}
			int pagesNumbers = Integer.parseInt(pages);
			if (pagesNumbers > 1000 || pagesNumbers < 1) {
				throw new RuntimeException("Book's pages should not be over 1000!");
			}
		} else if (type.equals("ebook")) {
			String fileFormatRegex = "epub|pdf|txt";
			if (!fileFormat.matches(fileFormatRegex)) {
				throw new RuntimeException("File format should be one of these: \"epub\", \"pdf\", \"txt\"");
			}
		}
		return new Book(type, title, author, genre, isbn, availability, dueDate, pages, fileFormat);
	}

	public String getType() {
		return this.type;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getGenre() {
		return this.genre;
	}

	public String getAvailability() {
		return this.availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getFileFormat() {
		return this.fileFormat;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public String getPages() {
		return this.pages;
	}

	@Override
	public String toString() {
		return getType() + "," + getTitle() + "," + getAuthor() + "," + getGenre() + "," + getIsbn() + "," + getAvailability() + "," + getDueDate() + "," + getPages() + "," + getFileFormat();
	}
}
