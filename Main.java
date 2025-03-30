import com.library.model.*;
import com.library.service.AuthenticationService;

import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to our library, please log in to verify your identity!");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Username: ");
		String username = scanner.nextLine().trim();
		System.out.print("Password: ");
		String password = scanner.nextLine().trim();
		try {
			User user = AuthenticationService.login(username, password);
			System.out.println("Your role is: " + user.getUserRole());
			switch (user.getUserRole()) {
				case "Admin" -> {
					Admin admin = (Admin) user;
					boolean isExit = false;
					while (!isExit) {
						System.out.println("Please select one of the following actions: \n1. Add a new user\n2. Remove a specific user\n3. View the list of all current users\n4. Exit");
						String userChoice = scanner.nextLine().trim();
						switch (userChoice) {
							case "1" -> {
								System.out.print("Please enter the username of the new user!\nUsername: ");
								String newUserUsername = scanner.nextLine().trim();
								System.out.print("Please enter the password of the new user!\nPassword: ");
								String newUserPassword = scanner.nextLine().trim();
								System.out.print("Please enter the role of the new user!\nRole: ");
								String newUserRole = scanner.nextLine().trim();
								User newUser = new User(newUserUsername, newUserPassword, newUserRole);
								admin.addNewUser(newUser);
								System.out.println("Succes  sfully added a new user!");
							}
							case "2" -> {
								System.out.print("Please enter the username of the user to remove!\nUsername: ");
								String usernameOfUserNeedRemove = scanner.nextLine().trim();
								admin.removeUser(usernameOfUserNeedRemove);
								System.out.println("Successfully removed the user!");
							}
							case "3" -> {
								admin.listAllUsers();
							}
							case "4" -> {
								isExit = true;
							}
							default -> throw new RuntimeException("Please select 1, 2, 3, 4!");
						}
					}
				}
				case "Librarian" -> {
					Librarian librarian = (Librarian) user;
					boolean isExit = false;
					while (!isExit) {
						System.out.println("Please select one of the following actions: \n1. Add a new book\n2. Remove a specific book\n3. View the list of all available books\n4. Exit");
						String userChoice = scanner.nextLine().trim();
						switch (userChoice) {
							case "1" -> {
								System.out.print("Please enter the type of this book!\nType: ");
								String newBookType = scanner.nextLine().trim().toLowerCase(Locale.US);
								System.out.print("Please enter the title of this book!\nTitle: ");
								String newBookTitle = scanner.nextLine().trim();
								System.out.print("Please enter the author of this book!\nAuthor: ");
								String newBookAuthor = scanner.nextLine().trim();
								System.out.print("Please enter the genre of this book!\nGenre: ");
								String newBookGenre = scanner.nextLine().trim().toLowerCase(Locale.US);
								System.out.print("Please enter the isbn code of this book!\nISBN: ");
								String newBookISBN = scanner.nextLine().trim();
								String newBookDueDate = "";
								switch (newBookType) {
									case "ebook" -> {
										String newBookAvailability = "not for loan";
										String newBookPages = "";
										System.out.print("Please enter the file format of this book!\nFormat: ");
										String newBookFileFormat = scanner.nextLine().trim().toLowerCase(Locale.US);
										Book newBook = Book.createBook(newBookType, newBookTitle, newBookAuthor, newBookGenre, newBookISBN, newBookAvailability, newBookDueDate, newBookPages, newBookFileFormat);
										librarian.addNewBook(newBook);
									}
									case "printed book" -> {
										String newBookAvailability = "available";
										System.out.print("Please enter the number of pages of this book!\nPages: ");
										String newBookPages = scanner.nextLine().trim();
										String newBookFileFormat = "";
										Book newBook = Book.createBook(newBookType, newBookTitle, newBookAuthor, newBookGenre, newBookISBN, newBookAvailability, newBookDueDate, newBookPages, newBookFileFormat);
										librarian.addNewBook(newBook);

									}
									default ->
											throw new RuntimeException("Book's type should be \"ebook\" or \"printed book\"");
								}
								System.out.println("Successfully added a new book!");
							}
							case "2" -> {
								System.out.print("Please enter the title of the book to remove!\nTitle: ");
								String titleOfBookNeedRemove = scanner.nextLine().trim();
								librarian.removeBook(titleOfBookNeedRemove);
								System.out.println("Successfully removed the book!");
							}
							case "3" -> {
								librarian.listAllBooks();
							}
							case "4" -> {
								isExit = true;
							}
							default -> throw new RuntimeException("Please select 1, 2, 3, 4!");
						}
					}
				}
				case "Reader" -> {
					Reader reader = (Reader) user;
					boolean isExit = false;
					while (!isExit) {
						System.out.println("Please select one of the following actions: \n1. Borrow a book\n2. Return a book\n3. View the list of all books\n4. Exit");
						String userChoice = scanner.nextLine().trim();
						switch (userChoice) {
							case "1" -> {
								System.out.print("Please enter the title of the book you want to borrow!\nTitle: ");
								String title = scanner.nextLine().trim();
								reader.borrowBook(title, reader.getUsername());
								System.out.println("You borrowed this book: " + title);
							}
							case "2" -> {
								System.out.print("Please enter the title of the book you want to return!\nTitle: ");
								String title = scanner.nextLine().trim();
								reader.returnBook(title, reader.getUsername());
								System.out.println("You returned this book: " + title);
							}
							case "3" -> {
								System.out.println("There are books you can borrow!");
								reader.viewAvailableBook();
							}
							case "4" -> {
								isExit = true;
							}
							default -> throw new RuntimeException("Please select 1, 2, 3, 4!");
						}
					}
				}
				default -> {
					throw new RuntimeException("Invalid role!");
				}
			}
			scanner.close();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
}