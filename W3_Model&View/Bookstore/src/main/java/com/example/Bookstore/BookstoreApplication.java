package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Book;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	public CommandLineRunner bookDemo(BookRepository repository) {
		return(args) -> {
			repository.save(new Book("A Farewell to Armns", "Ernest Hemingway","1232323-21",1929));
			repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945));
			
			System.out.println("IN COMMAND LINE RUNNER");
			
			for(Book book : repository.findAll()) {
				System.out.println(book.toString());
			}
		};
	}
}
